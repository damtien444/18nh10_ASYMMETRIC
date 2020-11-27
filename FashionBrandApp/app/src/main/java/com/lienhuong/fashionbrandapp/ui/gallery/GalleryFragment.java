package com.lienhuong.fashionbrandapp.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.model.Cart;
import com.lienhuong.fashionbrandapp.model.Order;

import java.util.ArrayList;
import java.util.LinkedList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private  AdapterCart myAdapter;
    private ArrayList<Order> listorder;
    private RecyclerView rv;
    private TextView txt_total;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        Cart list = new Cart();
        listorder = new ArrayList<Order>();
        rv = root.findViewById(R.id.listCart);
        txt_total = (TextView) root.findViewById(R.id.total);
        listorder = Cart.orders;
        Log.d("hello ne", String.valueOf(listorder));
        myAdapter = new AdapterCart((ArrayList<Order>) listorder, getContext());
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        int total = 0;
        for(int i = 0; i < Cart.orders.size(); i++){
            total = total + Cart.orders.get(i).getTien()*Cart.orders.get(i).getSo_luong();
        }
        txt_total.setText(String.valueOf(" "+total+" VND"));
        return root;
    }
}