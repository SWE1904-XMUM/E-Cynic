package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

import java.util.Comparator;

public class Order {
    public Integer orderId;
    public Integer userId;
    public Integer addressId;
    public Long date;
    public String status;

    public Order() {}

    // newly added -> order history
    public Order(@Nullable Integer orderId, @Nullable String status, Long date) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
    }

    public Order(@Nullable Integer orderId, Integer userId, Integer addressId, Long date, @Nullable String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.date = date;
        this.status = status;
    }

    public static Comparator<Order> NewestOrder = new Comparator<Order>()
    {
        @Override
        public int compare(Order o1, Order o2)
        {
            return o2.date.compareTo(o1.date);
        }
    };

    public static Comparator<Order> OldestOrder = new Comparator<Order>()
    {
        @Override
        public int compare(Order o1, Order o2)
        {
            return o1.date.compareTo(o2.date);
        }
    };

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", addressId=" + addressId +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
