package com.example.e_cynic.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.db.UserDatabase;
import com.example.e_cynic.session.SessionManager;
import com.example.e_cynic.utils.ValidationUtil;
import com.example.e_cynic.utils.userInteraction.SnackbarCreator;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText username, email, newPassword;
    private Button updateBtn;
    private String usernameTxt, emailTxt, newPasswordTxt;
    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        sm = new SessionManager(getApplicationContext());

        setViewComponent();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getViewText();
                if (validateFields() == true) {
                    if (!UserDatabase.checkUsernameExistence(usernameTxt)) {
                        SnackbarCreator.createNewSnackbar(updateBtn, "Account with the given username does " +
                                "not exist");
                    } else if (!UserDatabase.checkEmailExistence(emailTxt)) {
                        SnackbarCreator.createNewSnackbar(updateBtn, "Account with the given email does not" +
                                " exist");
                    } else {
                        boolean result = false;
                        try {
                            result = UserDatabase.editUserPassword(usernameTxt, emailTxt, newPasswordTxt);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (result == true) {
                            SnackbarCreator.createNewSnackbar(updateBtn, "Your password has been updated");
                            finish();
                        } else {
                            SnackbarCreator.createNewSnackbar(updateBtn, "Password update failed. Please try again");
                        }
                    }
                }
            }
        });
    }

    private void setViewComponent() {
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        newPassword = findViewById(R.id.newPassword);
        updateBtn = findViewById(R.id.updateBtn);
    }

    private void getViewText() {
        usernameTxt = username.getText().toString();
        emailTxt = email.getText().toString();
        newPasswordTxt = newPassword.getText().toString();
    }

    private boolean validateFields() {
        boolean complete = true;
        if (usernameTxt.equals("")) {
            setErrorField(username);
            complete = complete && false;
            SnackbarCreator.createNewSnackbar(updateBtn, "Username field is empty");
        } else if (!ValidationUtil.validateUsername(usernameTxt)) {
            setErrorField(username);
            complete = complete && false;
            SnackbarCreator.createNewSnackbar(updateBtn, "Please ensure your username contains 6 to 12 " +
                    "characters of alphabets and numbers");
        }
        else{
            resetField(username);
        }
        if (emailTxt.equals("")) {
            setErrorField(email);
            complete = complete && false;
            SnackbarCreator.createNewSnackbar(updateBtn, "Email field is empty");
        }
        else if(!ValidationUtil.validateEmail(emailTxt)) {
            setErrorField(email);
            complete = complete && false;
            SnackbarCreator.createNewSnackbar(updateBtn, "Please ensure your email is valid");
        }
        else{
            resetField(email);
        }
        if (newPasswordTxt.equals("")) {
            setErrorField(newPassword);
            complete = complete && false;
            SnackbarCreator.createNewSnackbar(updateBtn, "Password field is empty");
        }
        else if (!ValidationUtil.validatePassword(newPasswordTxt)){
            setErrorField(newPassword);
            complete = complete && false;
            SnackbarCreator.createNewSnackbar(updateBtn, "Please ensure your password contains upper case, lower case, and number, its length is at least 6");
        }
        else {
            resetField(newPassword);
        }
        return complete;
    }

    private void setErrorField(EditText et) {
        et.setBackgroundColor(getResources().getColor(R.color.error_background));
    }

    private void resetField(EditText et) {
        et.setBackgroundColor(getResources().getColor(R.color.grey));
    }
}