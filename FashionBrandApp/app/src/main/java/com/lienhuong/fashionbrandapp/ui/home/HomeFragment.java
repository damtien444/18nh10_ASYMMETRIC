package com.lienhuong.fashionbrandapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.bumptech.glide.Glide;
import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.lienhuong.fashionbrandapp.Home;
import com.lienhuong.fashionbrandapp.Interface.ItemClickListener;
import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.ViewHolder.MenuViewHolder;
import com.lienhuong.fashionbrandapp.itemDetail.ItemDetailsDisplayerActivity;
import com.lienhuong.fashionbrandapp.model.Category;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference category;
    RecyclerView recycler_menu;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;

    LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        recycler_menu = root.findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(root.getContext());
        recycler_menu.setLayoutManager(layoutManager);
        loadMenu();
        return root;
    }


    private void loadMenu() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Category");


        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(query, Category.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(options) {
            @NonNull
            @Override
            public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_item, parent, false);
                return new MenuViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MenuViewHolder viewHolder, int position, @NonNull Category model) {

                viewHolder.textMenuName.setText(model.getName());
                //Picasso.get(getApplicationContext()).load(model.getImage()).into(viewHolder.imageView);
                Glide.with(getContext()).load(model.getImage()).into(viewHolder.imageView);

                Log.d("LET SEE", "onBindViewHolder: "+model.getName());
                final Category clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick
                    ) {
                        Toast.makeText(getContext(), ""+clickItem.getName(), Toast.LENGTH_SHORT).show();

//                         goi den tri
                        Intent foodlist = new Intent(getContext(), ItemDetailsDisplayerActivity.class);
                        foodlist.putExtra("CategoryId", adapter.getRef(position).getKey());
                        startActivity(foodlist);
                    }
                });
            }

        };
        recycler_menu.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

}

