package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

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
    private ImageView redeemPointsBtn;
    private RecyclerView historyRecyclerView;
    HistoryArrayLists historyArrayLists;
    HistoryItemListAdapter historyItemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        setViewComponent();
        classDeclaration();
        setUpRecyclerView();
        bottomNavBar();

        redeemPointsBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(HistoryActivity.this,RedeemPointsActivity.class);
                startActivity(i);
            }
        });
    }

    private void setViewComponent()
    {
        redeemPointsBtn = findViewById(R.id.redeemPointsBtn);
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