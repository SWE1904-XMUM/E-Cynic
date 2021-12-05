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
    private Spinner state;
    private TextView uname,email,phone,password,addressLine1,addressLine2,addressLine3,postcode,city;
    SessionManager sm;

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
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        addressLine1 = findViewById(R.id.addressLine1);
        addressLine2 = findViewById(R.id.addressLine2);
        addressLine3 = findViewById(R.id.addressLine3);
        state = findViewById(R.id.state);
        postcode = findViewById(R.id.postcode);
        city = findViewById(R.id.city);
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

        try
        {
            addressList = AddressDatabase.getAddressesByUserId(user.userId);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("address size " + addressList.size());

        // TODO
        /*addressLine1.setText(addressList.get(0).firstLine);
        addressLine2.setText(addressList.get(0).secondLine);
        addressLine3.setText(addressList.get(0).thirdLine);
        //state.setText(addressList.get(0).state);
        postcode.setText(addressList.get(0).postcode);
        city.setText(addressList.get(0).city);*/
    }
}