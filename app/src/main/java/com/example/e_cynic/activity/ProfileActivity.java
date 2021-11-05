package com.example.e_cynic.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.e_cynic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity
{
    private Button about, editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        about = findViewById(R.id.about);
        editProfile = findViewById(R.id.editProfile);

        bottomNavBar();

        about.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(ProfileActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });
    }

    public void bottomNavBar()
    {
        // Initiate & assign variable
        BottomNavigationView btmNav = findViewById(R.id.btmNav);

        // Set selected layout
        btmNav.setSelectedItemId(R.id.profile);

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

                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });
    }
}