package com.example.e_cynic.entity;

import androidx.annotation.Nullable;

public class Item {
    public Integer itemId;
    public Integer orderId;
    public String itemName;
    public Integer numberOfItems;
    //TODO BLOB image
    public Double price;

    public Item() {}

    public Item(@Nullable Integer itemId, Integer orderId, String itemName, Integer numberOfItems,
                @Nullable Double price) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.itemName = itemName;
        this.numberOfItems = numberOfItems;
        this.price = price;
    }

}
