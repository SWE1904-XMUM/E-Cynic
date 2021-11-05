package com.example.e_cynic.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.e_cynic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity
{
    private ImageView info;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        info = findViewById(R.id.info);

        info.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(HomeActivity.this, AppInfoActivity.class);
                startActivity(i);
            }
        });

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
                        startActivity(new Intent(getApplicationContext(), RecycleActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                        overridePendingTransition(0, 0);
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