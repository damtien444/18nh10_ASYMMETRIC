package com.lienhuong.fashionbrandapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    EditText email, pass;
    TextView tv_register;
    Button btn_login;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        add();
    }

    private void add() {
        email=findViewById(R.id.email2);
        pass=findViewById(R.id.pass2);
        fAuth= FirebaseAuth.getInstance();
        tv_register=findViewById(R.id.tv_register);
        btn_login=findViewById(R.id.button2);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_1=email.getText().toString().trim();
                String pass_1=pass.getText().toString().trim();
                if(TextUtils.isEmpty(email_1))
                {
                    email.setError("Require Input Email!!");
                    return;
                }
                if(TextUtils.isEmpty(pass_1))
                {
                    pass.setError("Require Input Password!!");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email_1,pass_1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SignIn.this, "Login success!!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                        else{
                            Toast.makeText(SignIn.this, "Error "+task.getException() , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });
    }
}