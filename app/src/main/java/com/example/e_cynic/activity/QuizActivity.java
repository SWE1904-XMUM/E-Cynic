package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.session.SessionManager;
import com.example.e_cynic.utils.DateUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class QuizActivity extends AppCompatActivity
{
    private Button playBtn;
    private TextView noOfChance;
    String currentDate,date;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        sm = new SessionManager(getApplicationContext());

        setViewComponent();
        bottomNavBar();
        quizChance();

        playBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(QuizActivity.this,PlayQuizActivity.class);
                startActivity(i);
            }
        });
    }

    private void setViewComponent()
    {
        playBtn = findViewById(R.id.playBtn);
        noOfChance = findViewById(R.id.noOfChance);
    }

    private void quizChance()
    {
        date = sm.getDate();
        currentDate = DateUtil.getCurrentDate();

        if ("".equals(date))
        {
            playBtn.setEnabled(true);
            sm.setCurrentDate(currentDate);
            noOfChance.setText("1");
        }

        else
        {
            if (currentDate.equals(date))
            {
                playBtn.setEnabled(false);
                noOfChance.setText("0");
            }

            else
            {
                playBtn.setEnabled(true);
                sm.setCurrentDate(currentDate);
                noOfChance.setText("1");
            }
        }
    }

    public void bottomNavBar()
    {
        // Initiate & assign variable
        BottomNavigationView btmNav = findViewById(R.id.btmNav);

        // Set selected layout
        btmNav.setSelectedItemId(R.id.quiz);

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
}