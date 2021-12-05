package com.example.e_cynic.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.session.SessionManager;

public class ForgotPasswordActivity extends AppCompatActivity
{
    private TextView username,email,newPassword;
    private Button updateBtn;
    String usernameTxt,emailTxt,newPasswordTxt;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        sm = new SessionManager(getApplicationContext());

        setViewComponent();

        updateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getViewText();
                String uname = sm.getUsername();
                //TODO -> similar with sign up? validation??
            }
        });
    }

    private void setViewComponent()
    {
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        newPassword = findViewById(R.id.newPassword);
        updateBtn = findViewById(R.id.updateBtn);
    }

    private void getViewText()
    {
        usernameTxt = username.getText().toString();
        emailTxt = email.getText().toString();
        newPasswordTxt = newPassword.getText().toString();
    }
}