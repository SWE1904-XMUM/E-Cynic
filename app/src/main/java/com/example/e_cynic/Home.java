package com.example.e_cynic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        bottomNavBar();
    }

    public void bottomNavBar()
    {
        // Initiate & assign variable
        BottomNavigationView btmNav = findViewById(R.id.btmNav);

        // Set selected layout
        btmNav.setSelectedItemId(R.id.home);

        // Perform item selected listener
        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.home:
                        return true;

                    case R.id.recycle:
                        startActivity(new Intent(getApplicationContext(), Recycle.class));
                        overridePendingTransition(0, 0);
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

    /*
    private void btmNavListener()
    {
        BottomNavigationView btmNav = findViewById(R.id.btmNav);
        btmNav.setSelectedItemId(R.id.home);
        btmNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment f1 = new ArticlesFragment();
            Fragment f2 = new RecycleItemsFragment();
            Fragment f3 = new HistoryFragment();
            Fragment f4 = new ProfileFragment();

            FragmentManager fm = getSupportFragmentManager();

            switch (item.getItemId())
            {
                case R.id.home:
                    fm.beginTransaction().replace(R.id.homeFrame,f1).commit();
                    break;

                case R.id.recycle:
                    fm.beginTransaction().replace(R.id.homeFrame,f2).commit();
                    break;

                case R.id.history:
                    fm.beginTransaction().replace(R.id.homeFrame,f3).commit();
                    break;

                case R.id.profile:
                    fm.beginTransaction().replace(R.id.homeFrame,f4).commit();
                    break;
            }

            return false;
        }
    };
     */
}