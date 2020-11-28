package com.lienhuong.fashionbrandapp.itemDetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.model.Product;
import com.lienhuong.fashionbrandapp.ui.gallery.GalleryFragment;

import java.util.ArrayList;

public class ItemDetailsDisplayerActivity extends AppCompatActivity {
    private static final String INTENT_INPUT_KEY_CATEGORY = "category";
    private static final String FIREBASE_KEY_TO_CATEGORY = "Category";
    private static final String FIREBASE_KEY_TO_LIST_OF_EACH_ITEM = "danh_sach";

    private String category ;
    private Intent incomingIntent;

    private DatabaseReference firebaseReference;

    private RecyclerView rv;
    private ArrayList<Product> productToDisplayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details_displayer);
        getIncomingIntent();
        loadViews();
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                startActivity(new Intent(getApplicationContext(), GalleryFragment.class));
//
//            }
//        });
    }

    private void getIncomingIntent() {
        incomingIntent = getIntent();
        category = incomingIntent.getStringExtra(INTENT_INPUT_KEY_CATEGORY);
    }

    private void loadViews() {
        firebaseReference = FirebaseDatabase.getInstance().getReference();
        String pathToChild = FIREBASE_KEY_TO_CATEGORY + "/" + category + "/" + FIREBASE_KEY_TO_LIST_OF_EACH_ITEM;
        firebaseReference.child(pathToChild).addListenerForSingleValueEvent(new ValueEventListener() {
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
            Log.d("TAG", "productSnapshot --> " + productSnapshot.toString());
            Product temp = productSnapshot.getValue(Product.class);
            temp.setId(productSnapshot.getKey());
            productToDisplayList.add(temp);
        }
    }

    private void createRecycleView(ArrayList<Product> productToDisplayList) {
        rv = findViewById(R.id.rv_detailItemList);
        MyAdapter myAdapter = new MyAdapter(this, productToDisplayList, category);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}