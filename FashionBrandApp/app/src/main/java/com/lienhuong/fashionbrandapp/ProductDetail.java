package com.lienhuong.fashionbrandapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lienhuong.fashionbrandapp.model.Product;
import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {
    TextView product_name,product_price,product_description;
    ImageView product_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;
    String productID="";
    FirebaseDatabase database;
    DatabaseReference products;
    Product currentProduct;
    private String productCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //database
        database = FirebaseDatabase.getInstance();
        products = database.getReference("Products");

        //Init View
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);
//        btnCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Database(getBaseContext()).addToCart(new Product(productID,
//                        currentProduct.getName(),
//                        numberButton.getNumber(),
//                        currentProduct.getGia(),
//                Toast.makeText(ProductDetail.this, "Added to cart", Toast.LENGTH_SHORT).show();
//            }
//        });

        product_description = (TextView) findViewById(R.id.product_description);
        product_name = (TextView) findViewById(R.id.product_name);
        product_price = (TextView) findViewById(R.id.product_price);
        product_image = (ImageView) findViewById(R.id.img_product);

//        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
//
//        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
//        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        //Get product id from intent
        if(getIntent() != null){
            productID = getIntent().getStringExtra("ProductId");
            productCategory = getIntent().getStringExtra("productCategory");
            Log.d("TAG", "productID = " + productID );
        }
        if(!productID.isEmpty()){
            getDetailProduct();
        }
    }
    private void getDetailProduct(){
        products.child("Category/" + productCategory + "/danh_sach/" + productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", "snapshot = " + snapshot.getChildren() );
                currentProduct = snapshot.getValue(Product.class);

                //set Image
                Glide.with(getBaseContext()).load(currentProduct.getImage())
                        .into(product_image);
                collapsingToolbarLayout.setTitle(currentProduct.getName());

                product_price.setText(currentProduct.getGia());
                product_name.setText(currentProduct.getName());
                product_description.setText(currentProduct.getMota());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
