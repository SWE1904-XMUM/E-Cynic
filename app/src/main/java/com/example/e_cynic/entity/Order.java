package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    public Integer orderId;
    public Integer userId;
    public Integer addressId;
    public Date date;

    public Order(Integer orderId, Integer userId, Integer addressId, Date date) {
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", addresses=" + addressId +
                ", date=" + date +
                '}';
    }
}
