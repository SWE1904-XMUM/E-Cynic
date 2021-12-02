package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.session.SessionManager;

public class RedeemPointsActivity extends AppCompatActivity
{
    private ImageView backBtn;
    private TextView availablePoints;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redeem_points);

        sm = new SessionManager(getApplicationContext());
        setViewComponent();
        setAvailablePoints();

        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(RedeemPointsActivity.this,
                        ProfileActivity.class);
                startActivity(i);
            }
        });
    }

    private void setViewComponent()
    {
        backBtn = findViewById(R.id.backBtn);
        availablePoints = findViewById(R.id.availablePoints);
    }

    private void setAvailablePoints()
    {
        int p = sm.getTotalPoints();
        availablePoints.setText(String.valueOf(p));
    }
}