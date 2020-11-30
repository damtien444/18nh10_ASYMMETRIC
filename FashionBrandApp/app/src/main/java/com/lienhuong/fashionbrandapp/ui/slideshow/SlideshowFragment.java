package com.lienhuong.fashionbrandapp.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lienhuong.fashionbrandapp.R;
import com.lienhuong.fashionbrandapp.itemDetail.MyAdapter;
import com.lienhuong.fashionbrandapp.itemDetail.PostMessageAdapter;
import com.lienhuong.fashionbrandapp.model.PostMessage;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    FirebaseDatabase database;
    DatabaseReference firebaseReference;
    RecyclerView recyclerView_user;
    RecyclerView.LayoutManager layoutManager;
    PostMessageAdapter adapter;
    private ArrayList<PostMessage> productToDisplayList = new ArrayList<>();
    View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        database = FirebaseDatabase.getInstance();
        firebaseReference = database.getReference();

        recyclerView_user = root.findViewById(R.id.recycler_orders);
        recyclerView_user.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView_user.setLayoutManager(layoutManager);

        load();

        return root;
    }

    private void load() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Orders" + "/" + FirebaseAuth.getInstance().getUid());

        FirebaseRecyclerOptions<PostMessage> options = new FirebaseRecyclerOptions.Builder<PostMessage>()
                .setQuery(query, PostMessage.class)
                .build();

        Log.d("hi1", "load: " + FirebaseAuth.getInstance().getUid());

//        adapter = new PostMessageAdapter()
        firebaseReference.child("Orders" + "/" + FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                populateListFromData(dataSnapshot);
                createRecycleView(productToDisplayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


//        adapter = new FirebaseRecyclerAdapter<PostMessage, OrderViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull PostMessage model) {
//
//                holder.phone.setText(model.getPhone());
//                holder.address.setText(model.getAddress());
//                holder.name.setText(model.getName());
//                Log.d("hihi", "onBindViewHolder: "+model.getAddress());
//
////                LinearLayoutManager childLayout = new LinearLayoutManager(
////                        holder
////                            .recyclerView
////                            .getContext(),
////                        LinearLayoutManager.HORIZONTAL,
////                        false);
////
////                childLayout.setInitialPrefetchItemCount(
////                        model.getOrders().size());
////
////                OderItemAdapter oderItemAdapter = new OderItemAdapter(model.getOrders());
////
////                holder.recyclerView.setLayoutManager(childLayout);
////                holder.recyclerView.setAdapter(oderItemAdapter);
//
//
//            }
//
//            @NonNull
//            @Override
//            public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.oder_item, parent, false);
//                return new OrderViewHolder(view);
//            }
//        };
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.startListening();
//    }

    private void populateListFromData(DataSnapshot dataSnapshot) {
        for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
            Log.d("TAG", "productSnapshot --> " + productSnapshot.toString());
            PostMessage temp = productSnapshot.getValue(PostMessage.class);
            productToDisplayList.add(temp);
        }
    }

    private void createRecycleView(ArrayList<PostMessage> productToDisplayList) {
        PostMessageAdapter myAdapter = new PostMessageAdapter(productToDisplayList);
        recyclerView_user.setAdapter(myAdapter);
        recyclerView_user.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}