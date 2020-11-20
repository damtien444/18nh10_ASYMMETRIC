package com.lienhuong.fashionbrandapp.itemDetail;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.lienhuong.fashionbrandapp.R;

import java.util.ArrayList;

public class ItemDetailsDisplayerActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details_displayer);

        ArrayList<String> description = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            description.add("desc " + i);
        }

        rv = findViewById(R.id.rv_detailItemList);
        MyAdapter myAdapter = new MyAdapter(description);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }
}