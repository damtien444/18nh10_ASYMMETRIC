package com.lienhuong.fashionbrandapp.ui.gallery;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lienhuong.fashionbrandapp.ProductDetail;
import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.model.Cart;
import com.lienhuong.fashionbrandapp.model.Order;
import com.lienhuong.fashionbrandapp.model.PostMessage;

import java.util.ArrayList;
import java.util.LinkedList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private AdapterCart myAdapter;
    private ArrayList<Order> listorder;
    private RecyclerView rv;
    private TextView txt_total;
    private Button btn_place_order;
    private DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        Cart list = new Cart();
        listorder = new ArrayList<Order>();
        rv = root.findViewById(R.id.listCart);
        txt_total = root.findViewById(R.id.total);
        listorder = Cart.orders;
        Log.d("hello ne", String.valueOf(listorder));
        myAdapter = new AdapterCart(listorder, getContext());
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        btn_place_order = root.findViewById(R.id.btnPlaceOrder);
        Cart.setUID(FirebaseAuth.getInstance().getUid());
        reference = FirebaseDatabase.getInstance().getReference().child("Orders"+"/"+Cart.getUID());

        int total = 0;
        for (int i = 0; i < Cart.orders.size(); i++) {
            total = total + Cart.orders.get(i).getTien() * Cart.orders.get(i).getSo_luong();
        }


        txt_total.setText(" " + total + " VND");

        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                listorder.remove(viewHolder.getAdapterPosition());
                myAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(getContext(), "Your item has been deleted!", Toast.LENGTH_SHORT).show();
                int total = 0;
                for (int i = 0; i < Cart.orders.size(); i++) {
                    total = total + Cart.orders.get(i).getTien() * Cart.orders.get(i).getSo_luong();
                }
                txt_total.setText(" " + total + " VND");
            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv);
        return root;
    }

    private void showAlertDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("One More Step!");
            builder.setMessage("Enter Your phone and address:");
            final EditText edtName = new EditText(getContext());
            edtName.setHint("Your Name: ");
            final EditText edtPhone = new EditText(getContext());
            edtPhone.setHint("Phone number: ");
            final EditText edtAddress = new EditText(getContext());
            edtAddress.setHint("Your address: ");
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(edtName);
            linearLayout.addView(edtPhone);
            linearLayout.addView(edtAddress);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            linearLayout.setLayoutParams(lp);
//            edtPhone.setLayoutParams(lp);
            builder.setView(linearLayout);
//            builder.setView(edtAddress);
            builder.setIcon(R.drawable.ic_shopping_cart_black_24dp);

            builder.setPositiveButton("ĐẶT HÀNG", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PostMessage send = new PostMessage(Cart.getOrders(), Cart.getUID(),edtAddress.getText().toString(),edtPhone.getText().toString(),edtName.getText().toString());
                    reference.push().setValue(send);
                    Toast.makeText(getContext(), "Your order has been sent to our store!", Toast.LENGTH_SHORT).show();
                    btn_place_order.setClickable(false);
                    if (btn_place_order.isClickable())
                        btn_place_order.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                    else
                        btn_place_order.getBackground().setColorFilter(null);
                }
            });
            builder.show();
    }
}