package com.lienhuong.fashionbrandapp.ui.gallery;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
        reference = FirebaseDatabase.getInstance().getReference().child("Orders");

        int total = 0;
        for (int i = 0; i < Cart.orders.size(); i++) {
            total = total + Cart.orders.get(i).getTien() * Cart.orders.get(i).getSo_luong();
        }
        Cart.setUID(FirebaseAuth.getInstance().getUid());


        txt_total.setText(" " + total + " VND");

        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostMessage send = new PostMessage(Cart.getOrders(), Cart.getUID());
                reference.push().setValue(send);
                Toast.makeText(getContext(), "Your order has been sent to our store!", Toast.LENGTH_SHORT).show();
                btn_place_order.setClickable(false);
                if (btn_place_order.isClickable())
                    btn_place_order.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                else
                    btn_place_order.getBackground().setColorFilter(null);
            }
        });
        return root;
    }
}