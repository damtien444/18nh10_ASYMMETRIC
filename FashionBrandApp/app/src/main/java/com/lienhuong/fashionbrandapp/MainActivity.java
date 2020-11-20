package com.lienhuong.fashionbrandapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email, pass;
    TextView tv_register,sign_gg;
    Button btn_login;
    FirebaseAuth fAuth;
    private static final String TAG="";
    private final  static  int RC_SING_IN=111;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add();
    }

    private void add() {
        email=findViewById(R.id.email2);
        pass=findViewById(R.id.pass2);
        fAuth= FirebaseAuth.getInstance();
        tv_register=findViewById(R.id.tv_register);
        btn_login=findViewById(R.id.button2);
        sign_gg=findViewById(R.id.sign_gg2);

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
                            Toast.makeText(MainActivity.this, "Login success!!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Login again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        sign_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });
    }
    private void signIn() {
        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        Log.d(TAG, "firebaseAuthWithGoogle:"+mGoogleSignInClient);
        startActivityForResult(signIntent,RC_SING_IN);
    }
}