package com.example.e_cynic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity
{
    private Button loginBtn;
    private Button signUpBtn;
    private Button forgotPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        forgotPwd = findViewById(R.id.forgotPwd);

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Login.this,Home.class);
                startActivity(i);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });

        forgotPwd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Login.this,ForgotPassword.class);
                startActivity(i);
            }
        });
    }
}