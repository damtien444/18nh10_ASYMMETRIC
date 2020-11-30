package com.lienhuong.fashionbrandapp.itemDetail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.model.Order;

import java.util.List;

public class OderItemAdapter extends RecyclerView.Adapter<OderItemAdapter.OderViewHolder> {

    private List<Order> orders;
    private Context mContext;

    public OderItemAdapter(List<Order> orders) {
        this.orders = orders;

    }

    @NonNull
    @Override
    public OderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.oder, parent, false
                );
        return new OderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OderViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.tien.setText(order.getTien() + " VND");
        holder.ten.setText(order.getTen_san_pham());
        holder.so_luong.setText(order.getSo_luong() + "");
        Log.d("hehe", "onBindViewHolder: " + holder.ten.getText().toString() + " " + holder.so_luong.getText().toString());
    }


    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OderViewHolder extends RecyclerView.ViewHolder {
        public TextView ten;
        public TextView so_luong;
        public TextView tien;


        public OderViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.ten_hang);
            so_luong = itemView.findViewById(R.id.so_luong);
            tien = itemView.findViewById(R.id.tong_tien);
        }
    }
}
