package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    public Integer orderId;
    public Integer userId;
    public ArrayList<Item> items;
    public ArrayList<Address> addresses;
    public Date date;

    public Order(@Nullable Integer orderId, Integer userId, ArrayList<Item> items, ArrayList<Address> addresses,
                 Date date) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.addresses = addresses;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", items=" + items +
                ", addresses=" + addresses +
                ", date=" + date +
                '}';
    }
}
