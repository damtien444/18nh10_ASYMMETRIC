package com.lienhuong.fashionbrandapp.itemDetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.model.Product;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Product> localDataSet;
    private Product tempProduct;


    public MyAdapter(ArrayList<Product> dataSet) {
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
        tempProduct = localDataSet.get(position);
        viewHolder.populateData(tempProduct);
    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView textView_description;
        private final TextView textView_price;
        private final TextView textView_name;
        private final ImageView imageView;
        private int finalHeight, finalWidth;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            textView_description = (TextView) view.findViewById(R.id.description);
            textView_price = (TextView) view.findViewById(R.id.price);
            textView_name = (TextView) view.findViewById(R.id.name);
            imageView = view.findViewById(R.id.product_display_image);
        }

        public void populateData(Product targetProduct){
            this.textView_description.setText(targetProduct.getMota());
            this.textView_price.setText(targetProduct.getGia());
            this.textView_name.setText(targetProduct.getName());

            ViewTreeObserver vto = imageView.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                    finalHeight = imageView.getMeasuredHeight();
                    finalWidth = imageView.getMeasuredWidth();
                    Log.d("TAG", ":size: " + finalHeight + ", " + finalWidth);
                    Picasso.get().load(targetProduct.getImage()).resize(finalWidth,finalHeight).centerCrop()
                            .into(imageView);
                    return true;
                }
            });
        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "Clicked on " + textView_name.getText());
        }
    }
}
