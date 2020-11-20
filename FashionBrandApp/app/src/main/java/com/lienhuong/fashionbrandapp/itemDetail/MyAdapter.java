package com.lienhuong.fashionbrandapp.itemDetail;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.lienhuong.fashionbrandapp.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> localDataSet;

    public MyAdapter(ArrayList<String> dataSet) {
        Log.d("TAG", "init.... detail list "+ dataSet.size());
        localDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.detail_item_viewholder, viewGroup, false);
        Log.d("TAG", "detail list created");
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(localDataSet.get(position));
        Log.d("TAG", "inited "+ localDataSet.get(position));
    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.description);
        }
        public TextView getTextView() {
            return textView;
        }
    }
}
