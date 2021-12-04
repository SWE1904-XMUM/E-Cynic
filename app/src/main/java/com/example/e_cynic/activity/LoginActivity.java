package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.session.SessionManager;

public class LoginActivity extends AppCompatActivity
{
    // views
    private Button loginBtn, signUpBtn, forgotPwd;
    private TextView username,password;

    // text of view
    private String usernameTxt,passwordTxt;

    // delay
    private int signUpDelay = 1000;

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
                // TODO remove login (after done)
/*
                updateViewText();

                if (!fieldDataIsComplete())
                {
                    SnackbarCreator.createNewSnackbar(view,"Please enter all field.");
                }

                else
                {
                    boolean checkUserExistence = UserDatabase.checkUsernameExistence(usernameTxt);

                    if (checkUserExistence == false)
                    {
                        SnackbarCreator.createNewSnackbar(view,"Not an existing user, please sign up.");

                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Intent signUp = new Intent(LoginActivity.this,SignUpActivity.class);
                                startActivity(signUp);
                            }
                        },signUpDelay);
                    }

                    else
                    {
                        boolean verify = UserDatabase.verifyUser(usernameTxt,passwordTxt);

                        if (verify == true)
                        {
                            //Store login in session
                            sm.setLogin(true);

                            // Store username
                            sm.setUsername(usernameTxt);

                            // Redirect to home page
                            Intent homePage = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(homePage);
                        }

                        else
                        {
                            SnackbarCreator.createNewSnackbar(view,"Invalid username or password!");
                        }
                    }
                }
*/
                Intent homePage = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(homePage);
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