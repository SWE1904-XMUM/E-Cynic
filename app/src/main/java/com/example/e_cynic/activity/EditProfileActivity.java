package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.db.AddressDatabase;
import com.example.e_cynic.db.UserDatabase;
import com.example.e_cynic.entity.Address;
import com.example.e_cynic.entity.User;
import com.example.e_cynic.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity
{
    private ImageView backBtn;
    private TextView uname,email,phone,password;
    SessionManager sm;
    String unameTxt,emailTxt,phoneTxt,passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        sm = new SessionManager(getApplicationContext());

        setUpViewComponent();
        displayProfileData();

        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(EditProfileActivity.this,
                        ProfileActivity.class);
                startActivity(i);
            }
        });
    }

    private void setUpViewComponent()
    {
        backBtn = findViewById(R.id.backBtn);
        uname = findViewById(R.id.uname);
        uname.setEnabled(false);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
    }

    private void getViewText()
    {
        unameTxt = uname.getText().toString();
        emailTxt = email.getText().toString();
        phoneTxt = phone.getText().toString();
        passwordTxt = password.getText().toString();
    }

    private void displayProfileData()
    {
        //TODO
        String username = "testuser";//sm.getUsername();
        User user = new User();
        List<Address> addressList = new ArrayList<>();

        try
        {
            user = UserDatabase.getUserInfoByUsername(username);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        uname.setText(username);
        email.setText(user.email);
        phone.setText(user.phoneNumber);
        password.setText(user.phoneNumber);
    }

    private void updateEditedProfile()
    {
        getViewText();
        //same code with sign up?
    }
}