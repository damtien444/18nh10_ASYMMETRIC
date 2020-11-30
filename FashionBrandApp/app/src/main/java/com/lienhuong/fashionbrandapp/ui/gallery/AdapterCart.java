package com.lienhuong.fashionbrandapp.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.model.Cart;
import com.lienhuong.fashionbrandapp.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyViewHolder>{
    private ArrayList<Order> mContacts;
    private ArrayList<Order> listContacts;
    private Context mContext;
    int total = 0;

    public AdapterCart(ArrayList<Order> mContacts, Context mContext) {

        this.mContacts = mContacts;
        this.mContext = mContext;
        listContacts = new ArrayList<Order>(mContacts);
        listContacts = mContacts;
        Log.d("DEBUG3", String.valueOf((ArrayList) mContacts));

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_price.setText(String.valueOf(mContacts.get(position).getTien()));
        holder.txt_cart_name.setText(mContacts.get(position).getTen_san_pham());
        holder.txt_cart_count.setText(String.valueOf(mContacts.get(position).getSo_luong()));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView txt_cart_name,txt_price,txt_cart_count;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            txt_cart_name = (TextView) view.findViewById(R.id.cart_item_name);
            txt_price = (TextView) view.findViewById(R.id.cart_item_price);
            txt_cart_count = (TextView) view.findViewById(R.id.cart_item_count);
        }
        
    }
}
