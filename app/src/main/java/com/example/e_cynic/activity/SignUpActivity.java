package com.example.e_cynic.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.db.AddressDatabase;
import com.example.e_cynic.db.UserDatabase;
import com.example.e_cynic.entity.Address;
import com.example.e_cynic.entity.User;
import com.example.e_cynic.utils.userInteraction.SnackbarCreator;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity
{

    public final long DELAY = 1000;
    // views
    private EditText username;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText postcode;
    private EditText city;
    private EditText addressLine1;
    private EditText addressLine2;
    private EditText addressLine3;
    private Spinner state;
    private Button signUpBtn;

    // text of views
    private String usernameTxt;
    private String emailTxt;
    private String phoneTxt;
    private String passwordTxt;
    private String postcodeTxt;
    private String cityTxt;
    private String addressLine1Txt;
    private String addressLine2Txt;
    private String addressLine3Txt;
    String stateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        setViewComponent();

        signUpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                updateViewText();

                if (!fieldDataIsComplete())
                {
                    SnackbarCreator.createNewSnackbar(view,"Please fill in required field.");
                    return;
                }

                if(UserDatabase.checkUsernameExistence(usernameTxt) == true)
                {
                    SnackbarCreator.createNewSnackbar(view,"Username already exist, please try another one.");
                    setErrorField(username);
                    return;
                }

                if(UserDatabase.checkEmailExistence(emailTxt) == true) {
                    SnackbarCreator.createNewSnackbar(view,"Account with the given email already exist");
                    setErrorField(email);
                    return;
                }

                User user = new User(null,usernameTxt,emailTxt,passwordTxt,phoneTxt);
                boolean insertUser = false;
                try {
                    insertUser = UserDatabase.insertUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (insertUser == true)
                {
                    Integer userId = -1;

                    try {
                        userId = UserDatabase.getUserIdByUsername(usernameTxt);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (userId > 0)
                    {
                        Address address = new Address(null,userId,addressLine1Txt,
                                addressLine2Txt, addressLine3Txt,cityTxt,stateTxt,
                                !postcodeTxt.equals("")?Integer.parseInt(postcodeTxt):0);
                        boolean insertAddress = false;
                        try {
                            insertAddress = AddressDatabase.insertAddress(address);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        if (insertAddress)
                        {
                            SnackbarCreator.createNewSnackbar(view,"Successfully sign up!");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                }
                            }, DELAY);
                        }
                    }
                }
            }
        });
    }

    private void setViewComponent() {
        username = findViewById(R.id.uname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        state = findViewById(R.id.state);
        postcode = findViewById(R.id.postcode);
        city = findViewById(R.id.city);
        addressLine1 = findViewById(R.id.addressLine1);
        addressLine2 = findViewById(R.id.addressLine2);
        addressLine3 = findViewById(R.id.addressLine3);
        signUpBtn = findViewById(R.id.signUpBtn);

        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(SignUpActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.state));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(myAdapter);
    }

    private boolean fieldDataIsComplete() {
        boolean complete = true;
        if(usernameTxt.equals("")) {
            setErrorField(username);
            complete = complete && false;
        }
        else {
            resetField(username);
        }
        if(emailTxt.equals("")) {
            setErrorField(email);
            complete = complete && false;
        }
        else {
            resetField(email);
        }
        if(phoneTxt.equals("")) {
            setErrorField(phone);
            complete = complete && false;
        }
        else {
            resetField(phone);
        }
        if(passwordTxt.equals("")) {
            setErrorField(password);
            complete = complete && false;
        }
        else {
            resetField(password);
        }
        if(addressLine1Txt.equals("")) {
            setErrorField(addressLine1);
            complete = complete && false;
        }
        else {
            resetField(addressLine1);
        }
        if(postcodeTxt.equals("")) {
            setErrorField(postcode);
            complete = complete && false;
        }
        else {
            resetField(postcode);
        }
        if(cityTxt.equals("")) {
            setErrorField(city);
            complete = complete && false;
        }
        else {
            resetField(city);
        }
        return complete;
    }

    private void setErrorField(EditText et) {
        et.setBackgroundColor(getResources().getColor(R.color.error_background));
    }

    private void resetField(EditText et) {
        et.setBackgroundColor(getResources().getColor(R.color.grey));
    }

    private void updateViewText() {
        usernameTxt = username.getText().toString();
        emailTxt = email.getText().toString();
        phoneTxt = phone.getText().toString();
        passwordTxt = password.getText().toString();
        postcodeTxt = postcode.getText().toString();
        cityTxt = city.getText().toString();
        addressLine1Txt = addressLine1.getText().toString();
        addressLine2Txt = addressLine2.getText().toString();
        addressLine3Txt = addressLine3.getText().toString();
        stateTxt = state.getSelectedItem().toString();
    }
}