package com.example.e_cynic.entity;

public class Order {
    public Integer orderId;
    public Integer userId;
    public Integer addressId;
    public Long date;

    public Order() {}

    public Order(Integer orderId, Integer userId, Integer addressId, long date) {
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
