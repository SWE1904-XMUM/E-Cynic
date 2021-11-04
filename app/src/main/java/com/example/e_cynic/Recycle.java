package com.example.e_cynic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Recycle extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

        bottomNavBar();
    }

    private void bottomNavBar()
    {
        // Initiate & assign variable
        BottomNavigationView btmNav = findViewById(R.id.btmNav);

        // Set home as selected
        btmNav.setSelectedItemId(R.id.recycle);

        // Perform item selected listener
        {
            btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
            {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item)
                {
                    switch (item.getItemId())
                    {
                        case R.id.home:
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.recycle:
                            return true;

                        case R.id.history:
                            startActivity(new Intent(getApplicationContext(), History.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.profile:
                            startActivity(new Intent(getApplicationContext(), Profile.class));
                            overridePendingTransition(0, 0);
                            return true;
                    }
                    return false;
                }
            });
        }
    }
}