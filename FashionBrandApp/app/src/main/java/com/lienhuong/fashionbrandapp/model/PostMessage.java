package com.lienhuong.fashionbrandapp.model;


import java.util.ArrayList;
import java.util.LinkedList;

public class PostMessage {

    private ArrayList<Order> orders;
    private String UID;
    private String address;
    private String phone;
    private String name;

    public PostMessage() {
    }

    public PostMessage(ArrayList<Order> orders, String UID, String address, String phone, String name) {
        this.orders = orders;
        this.UID = UID;
        this.address = address;
        this.phone = phone;
        this.name = name;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

