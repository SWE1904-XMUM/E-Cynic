package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.adapter.OrderDetailsAdapter;
import com.example.e_cynic.adapter.RecycleAddItemAdapter;
import com.example.e_cynic.db.AddressDatabase;
import com.example.e_cynic.db.ItemDatabase;
import com.example.e_cynic.db.OrderDatabase;
import com.example.e_cynic.entity.Address;
import com.example.e_cynic.entity.Item;
import com.example.e_cynic.entity.Order;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private Intent intent;

    //Views
    private RecyclerView rv_itemList;
    private TextView tv_noOfDevice;
    private TextView tv_address;
    private TextView tv_point;
    private TextView tv_status;

    //data
    private Integer orderId;
    private Order order;
    private List<Item> itemList;
    private Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        setViewComponents();
        intent = getIntent();

        orderId = Integer.valueOf(intent.getStringExtra("orderId"));

        try {
            order = OrderDatabase.getOrderByOrderId(orderId);
            itemList = ItemDatabase.getItemsByOrderId(orderId);

            address = AddressDatabase.getAddressByAddressId(order.addressId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (itemList != null) {
            updateView();
        }
    }

    private void updateView() {
        //TODO display item list
    }

    private void setViewComponents() {
        rv_itemList = findViewById(R.id.rv_itemList);
        tv_noOfDevice = findViewById(R.id.noOfDevice);
        tv_address = findViewById(R.id.pinnedAddress);
        tv_status = findViewById(R.id.status);
        tv_point = findViewById(R.id.point);
    }
}