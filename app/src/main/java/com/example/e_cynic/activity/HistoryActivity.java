package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.e_cynic.R;
import com.example.e_cynic.adapter.HistoryOrderListAdapter;
import com.example.e_cynic.entity.Item;
import com.example.e_cynic.entity.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class HistoryActivity extends AppCompatActivity
{

    private RecyclerView historyRecyclerView;
    HistoryOrderListAdapter historyOrderListAdapter;
    private String[] itemInSortList;
    private Spinner sortList;

    // items list
    List<Order> historyOrders;
    List<Item> historyItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        setViewComponent();
        setSortList();
        storeDataIntoList();
        //setUpRecyclerView();
        bottomNavBar();
    }

    private void setViewComponent()
    {
        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        sortList = (Spinner) findViewById(R.id.spinner);
    }

    private void setSortList()
    {
        //Sort drop down list
        itemInSortList = getResources().getStringArray(R.array.sortListEg);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(HistoryActivity.this,
                android.R.layout.simple_list_item_1, itemInSortList);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortList.setAdapter(myAdapter); // show data
    }

    private void storeDataIntoList()
    {
        //
    }

    private void setUpRecyclerView()
    {
        historyOrderListAdapter = new HistoryOrderListAdapter(getApplicationContext(),historyOrders,historyItem);
        historyRecyclerView.setAdapter(historyOrderListAdapter);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
    }

    public void bottomNavBar()
    {
        // Initiate & assign variable
        BottomNavigationView btmNav = findViewById(R.id.btmNav);

        // Set selected layout
        btmNav.setSelectedItemId(R.id.history);

        // Perform item selected listener
        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.recycle:
                        startActivity(new Intent(getApplicationContext(), RecycleActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.quiz:
                        startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.history:
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}