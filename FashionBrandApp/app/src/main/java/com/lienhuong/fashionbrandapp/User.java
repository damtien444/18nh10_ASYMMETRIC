package com.lienhuong.fashionbrandapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {
    String Name;
    String Password;
    public User(){

    }

    public User (String name,String password){
        Name = name;
        Password = password;
    }

    public String getName(){
        return Name;
    }
    public void setName(String name){
        Name = name;
    }

    public String getPassword(){
        return Password;
    }
    public void setPassword(String password){
        Name = password;
    }
}
