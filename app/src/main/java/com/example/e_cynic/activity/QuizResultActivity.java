package com.example.e_cynic.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.session.SessionManager;

public class QuizResultActivity extends AppCompatActivity
{
    private TextView scoreValue;
    private Button returnBtn;
    private int totalPoints;

    // session manager
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_result);

        scoreValue = findViewById(R.id.scoreValue);
        returnBtn = findViewById(R.id.returnBtn);

        int score = getIntent().getIntExtra("CorrectAns",0);
        scoreValue.setText(score + " / 5");

        returnBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(QuizResultActivity.this,QuizActivity.class);
                startActivity(intent);
            }
        });

        sm = new SessionManager(getApplicationContext());
        totalPoints = sm.getTotalPoints();;
        totalPoints += score;
        sm.setTotalPoints(totalPoints);
    }
}