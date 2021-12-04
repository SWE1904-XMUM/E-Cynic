package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.db.ItemDatabase;
import com.example.e_cynic.entity.Item;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private Intent intent;
    private Integer orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        intent = getIntent();
        orderId = Integer.valueOf(intent.getStringExtra("orderId"));
        List<Item> itemList = null;

        try {
            itemList = ItemDatabase.getItemsByOrderId(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(itemList != null) {
            //TODO display item list
        }


    }
}