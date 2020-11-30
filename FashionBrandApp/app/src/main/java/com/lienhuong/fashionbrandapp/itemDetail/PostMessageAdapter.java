package com.lienhuong.fashionbrandapp.itemDetail;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.model.PostMessage;

import java.util.List;

public class PostMessageAdapter extends RecyclerView.Adapter<PostMessageAdapter.OrderViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<PostMessage> itemList;

    public PostMessageAdapter(List<PostMessage> itemList) {
        this.itemList = itemList;
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.oder_item,
                        parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        PostMessage parentItem
                = itemList.get(position);

        holder.initData(parentItem);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(  holder.recyclerView
//                .getContext(),
//                LinearLayoutManager.VERTICAL,
//                false);
//
//        layoutManager
//                .setInitialPrefetchItemCount(
//                        parentItem
//                                .getOrders()
//                                .size());

        OderItemAdapter childItemAdapter
                = new OderItemAdapter(
                parentItem
                        .getOrders());
        Log.d("TAG1", "onBindViewHolder: " + parentItem.getOrders());

        holder
                .recyclerView
                .setLayoutManager(new LinearLayoutManager(holder.recyclerView.getContext()));
        holder
                .recyclerView
                .setAdapter(childItemAdapter);
//        holder
//                .recyclerView
//                .setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {


        public RecyclerView recyclerView;
        public TextView address;
        public TextView phone;
        public TextView name;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.oder_name);
            address = (TextView) itemView.findViewById(R.id.order_diachi);
            phone = (TextView) itemView.findViewById(R.id.order_phone);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_productOfOder);


        }


        public void initData(PostMessage postMessage) {

            name.setText(postMessage.getName());
            address.setText(postMessage.getAddress());
            phone.setText(postMessage.getPhone());


        }

    }
}
