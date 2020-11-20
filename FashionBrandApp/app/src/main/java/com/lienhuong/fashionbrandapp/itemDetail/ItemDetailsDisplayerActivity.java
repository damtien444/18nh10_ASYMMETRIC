package com.lienhuong.fashionbrandapp.itemDetail;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.model.Product;

import java.util.ArrayList;

public class ItemDetailsDisplayerActivity extends AppCompatActivity {
    private RecyclerView rv;
    private String category;
    private Intent incomingIntent;
    private ArrayList<Product> productToDisplayList = new ArrayList<>();
    private DatabaseReference firebaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details_displayer);

        firebaseReference = FirebaseDatabase.getInstance().getReference();
        incomingIntent = getIntent();
        category = incomingIntent.getStringExtra("category");

        loadView();
    }

    private void loadView() {
        firebaseReference.child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                populateListFromData(dataSnapshot);
                createRecycleView(productToDisplayList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void populateListFromData(DataSnapshot dataSnapshot) {
        for(DataSnapshot productSnapshot: dataSnapshot.getChildren()){
            productToDisplayList.add(productSnapshot.getValue(Product.class));
        }
    }

    private void createRecycleView(ArrayList<Product> productToDisplayList) {
        rv = findViewById(R.id.rv_detailItemList);
        MyAdapter myAdapter = new MyAdapter(productToDisplayList);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}