package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class Item {
    public Integer itemId;
    public Integer orderId;
    public String itemName;
    public Integer numberOfItems;
    public byte[] image;
    public Double price;

    public Item(){}

    public Item(@Nullable Integer itemId, Integer orderId, String itemName, Integer numberOfItems,
                byte[] image, @Nullable Double price) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.itemName = itemName;
        this.numberOfItems = numberOfItems;
        this.image = image;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", orderId=" + orderId +
                ", itemName='" + itemName + '\'' +
                ", numberOfItems=" + numberOfItems +
                ", image=" + Arrays.toString(image) +
                ", price=" + price +
                '}';
    }
}
