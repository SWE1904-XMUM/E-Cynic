package com.example.e_cynic.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.e_cynic.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayQuizActivity extends AppCompatActivity
{
    private TextView questionNo;
    private TextView question;
    private TextView score;
    private RadioButton ans1;
    private RadioButton ans2;
    private RadioButton ans3;
    private RadioButton ans4;
    private Button confirmBtn;

    private String correctAns;
    private int correctAnsCount = 0;
    private int qCount = 1;
    static final private int qc = 5;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            // {"question","correct ans","ans","ans"}
            {"Q1","ans1","ans2","ans3","ans4"},
            {"Q2","ans1","ans2","ans3","ans4"},
            {"Q3","ans1","ans2","ans3","ans4"},
            {"Q4","ans1","ans2","ans3","ans4"},
            {"Q5","ans1","ans2","ans3","ans4"},
            {"Q6","ans1","ans2","ans3","ans4"},
            {"Q7","ans1","ans2","ans3","ans4"},
            {"Q8","ans1","ans2","ans3","ans4"},
            {"Q9","ans1","ans2","ans3","ans4"},
            {"Q10","ans1","ans2","ans3","ans4"}};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_quiz);

        questionNo = findViewById(R.id.questionNo);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        confirmBtn = findViewById(R.id.confirmBtn);

        int l = quizData.length;

        for (int i = 0; i < l; i++)
        {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(quizData[i][0]);
            temp.add(quizData[i][1]);
            temp.add(quizData[i][2]);
            temp.add(quizData[i][3]);
            temp.add(quizData[i][4]);

            quizArray.add(temp);
        }

        nextQuestion();
    }

    public void nextQuestion()
    {
        questionNo.setText("Q" + qCount);
        Random r = new Random();

        // Generate random number between quiz size
        int randomNo = r.nextInt(quizArray.size());

        // Pick question
        ArrayList<String> quiz = quizArray.get(randomNo);

        // Set question and right answer
        // quizData: {"question","rightAns","ans","ans","ans}
        question.setText(quiz.get(0));
        correctAns = quiz.get(1);

        // Remove question from array list & shuffle the choices
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set choices
        ans1.setText(quiz.get(0));
        ans2.setText(quiz.get(1));
        ans3.setText(quiz.get(2));
        ans4.setText(quiz.get(3));

        // Remove the displayed question from array
        quizArray.remove(randomNo);
    }

    public void checkAns(View view)
    {
        // Get clicked button
        Button ansBtn = findViewById(view.getId());
        String textBtn = ansBtn.getText().toString();
        String alertTitle;

        if (textBtn.equals(correctAns))
        {
            alertTitle = "Correct!";
            correctAnsCount++;
            // TODO -> display current score
            //score.setText(correctAnsCount);
        }

        else
        {
            alertTitle = "Wrong answer.";
        }

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(alertTitle);
        ad.setMessage("Answer: " + correctAns);
        ad.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if (qCount == qc)
                {
                    Intent intent = new Intent(getApplicationContext(), QuizResultActivity.class);
                    intent.putExtra("CorrectAns", correctAnsCount);
                    startActivity(intent);
                }

                else
                {
                    qCount++;
                    nextQuestion();
                }
            }
        });

        ad.setCancelable(false);
        ad.show();
    }
}