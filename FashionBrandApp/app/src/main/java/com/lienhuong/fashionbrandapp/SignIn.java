package com.lienhuong.fashionbrandapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    Button SignIn;
    EditText phone,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        phone = (EditText)findViewById(R.id.phone);
        pass = (EditText)findViewById(R.id.pass);
        SignIn = (Button)findViewById(R.id.SignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please waiting..");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(phone.getText().toString()).exists()){

                        mDialog.dismiss();
                        User user = dataSnapshot.child(phone.getText().toString()).getValue(User.class);
                        if(user.getPassword().equals(pass.getText().toString())){
                            Toast.makeText(SignIn.this,"Sign in successfully", Toast.LENGTH_SHORT).show();
                            // TODO: Gọi đến home
                        }
                        else{
                            Toast.makeText(SignIn.this,"Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this,"User not exist in database", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}