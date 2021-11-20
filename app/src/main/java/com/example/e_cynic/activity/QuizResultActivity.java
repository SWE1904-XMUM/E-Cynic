package com.example.e_cynic.activity;
import com.example.e_cynic.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity
{
    private TextView scoreValue;
    private Button returnBtn;

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

        //TODO: could implement with session manager for later use
        /*totalScore = findViewById(R.id.totalScore);
        totalScore.setText("Total score: " + tScore);
        SharedPreferences sp = getSharedPreferences("quizResult", Context.MODE_PRIVATE);
        int tScore = sp.getInt("Total Score",0);

        tScore += score;
        //Update total score
        SharedPreferences.Editor edt = sp.edit();
        edt.putInt("Total Score",tScore);
        edt.commit();*/
    }
}