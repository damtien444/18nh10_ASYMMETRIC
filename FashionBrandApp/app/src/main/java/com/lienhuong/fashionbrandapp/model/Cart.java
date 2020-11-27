package com.lienhuong.fashionbrandapp.model;

import java.util.LinkedList;

public class Cart {
    private static LinkedList<Order> orders = new LinkedList<>();


    public static boolean addToCart(Order order) {
        try {
            Cart.orders.add(order);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean addToCart(String id_san_pham, String id_category, String ten_san_pham, int so_luong, int tien) {
        try {
            Order order = new Order(id_san_pham, id_category, ten_san_pham, so_luong, tien);
            Cart.orders.add(order);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    private static String UID;

    public static LinkedList<Order> getOrders() {
        return orders;
    }

    public static void setOrders(LinkedList<Order> orders) {
        Cart.orders = orders;
    }

    public static String getUID() {
        return UID;
    }

    public static void setUID(String UID) {
        Cart.UID = UID;
    }
}
