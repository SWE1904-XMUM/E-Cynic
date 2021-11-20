package com.example.e_cynic.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.e_cynic.R;

public class RedeemPointsActivity extends AppCompatActivity
{
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redeem_points);

        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(RedeemPointsActivity.this,
                        HistoryActivity.class);
                startActivity(i);
            }
        });
    }
}