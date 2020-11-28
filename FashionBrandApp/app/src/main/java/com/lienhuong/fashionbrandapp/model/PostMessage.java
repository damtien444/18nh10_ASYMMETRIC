package com.lienhuong.fashionbrandapp.model;


import java.util.ArrayList;
import java.util.LinkedList;

public class PostMessage {

    private ArrayList<Order> orders;
    private String UID;

    public PostMessage() {
    }

    public PostMessage(ArrayList orders, String UID) {
        this.orders = orders;
        this.UID = UID;
    }

    public ArrayList getOrders() {
        return orders;
    }

    public void setOrders(ArrayList orders) {
        this.orders = orders;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}

