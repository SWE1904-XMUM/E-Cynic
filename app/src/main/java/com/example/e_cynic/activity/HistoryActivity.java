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
import com.example.e_cynic.adapter.HistoryItemListAdapter;
import com.example.e_cynic.arrayList.HistoryArrayLists;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HistoryActivity extends AppCompatActivity
{

    private RecyclerView historyRecyclerView;
    HistoryArrayLists historyArrayLists;
    HistoryItemListAdapter historyItemListAdapter;
    private String[] itemInSortList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        setViewComponent();
        classDeclaration();
        setUpRecyclerView();
        bottomNavBar();

        //Sort drop down list
        Spinner sortList = (Spinner) findViewById(R.id.spinner);
        itemInSortList = getResources().getStringArray(R.array.sortListEg);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(HistoryActivity.this,
                android.R.layout.simple_list_item_1, itemInSortList);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortList.setAdapter(myAdapter); // show data

    }

    private void setViewComponent()
    {
        historyRecyclerView = findViewById(R.id.historyRecyclerView);
    }

    private void classDeclaration()
    {
        historyArrayLists = new HistoryArrayLists();
        historyItemListAdapter = new HistoryItemListAdapter(getApplicationContext(),
                HistoryArrayLists.itemImage,
                HistoryArrayLists.itemName,
                HistoryArrayLists.numberOfItems,
                HistoryArrayLists.price,
                HistoryArrayLists.date);
    }

    private void setUpRecyclerView()
    {
        historyRecyclerView.setAdapter(historyItemListAdapter);
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