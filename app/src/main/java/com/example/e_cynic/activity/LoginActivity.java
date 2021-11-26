package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.session.SessionManager;
import com.example.e_cynic.utils.userInteraction.SnackbarCreator;

public class LoginActivity extends AppCompatActivity
{
    // views
    private Button loginBtn, signUpBtn, forgotPwd;
    private TextView username,password;

    // text of view
    private String usernameTxt,passwordTxt;

    // session manager
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        setViewComponent();

        // Initialize session manager
        sm = new SessionManager(getApplicationContext());

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                updateViewText();

                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);

                if (!fieldDataIsComplete())
                {
                    SnackbarCreator.createNewSnackbar(view,"Please enter all field.");
                }

                else
                {
                    /*
                    //TODO -> link to database
                    //Store login in session
                    sm.setLogin(true);

                    // Store username
                    sm.setUsername(usernameTxt);

                    // Redirect to home page*/
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        forgotPwd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
    }

    private void setViewComponent()
    {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        forgotPwd = findViewById(R.id.forgotPwd);
    }

    private void updateViewText()
    {
        usernameTxt = username.getText().toString();
        passwordTxt = password.getText().toString();
    }

    private boolean fieldDataIsComplete()
    {
        return !(usernameTxt.equals("")||passwordTxt.equals(""));
    }
}