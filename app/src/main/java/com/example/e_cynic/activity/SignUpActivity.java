package com.example.e_cynic.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.e_cynic.R;
import com.example.e_cynic.db.AddressDatabase;
import com.example.e_cynic.db.UserDatabase;
import com.example.e_cynic.entity.Address;
import com.example.e_cynic.entity.User;
import com.example.e_cynic.utils.userInteraction.SnackbarCreator;

public class SignUpActivity extends AppCompatActivity
{
    //views
    TextView username,email,phone,password, postcode,city,addressLine1,addressLine2,addressLine3;
    Spinner state;
    Button signUpBtn;

    //text of view
    String usernameTxt, emailTxt, phoneTxt, passwordTxt, postcodeTxt, cityTxt, addressLine1Txt, addressLine2Txt, addressLine3Txt, stateTxt;

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

                if(UserDatabase.checkUsernameExistence(usernameTxt)==true)
                {
                    SnackbarCreator.createNewSnackbar(view,"Username already exist, please try another one.");
                    return;
                }

                User user = new User(null,usernameTxt,emailTxt,passwordTxt,phoneTxt);
                boolean insertUser = UserDatabase.insertUser(user);

                if (insertUser == true)
                {
                    int userId = UserDatabase.getUserIdByUsername(usernameTxt);

                    if (userId > 0)
                    {
                        Address address = new Address(null,userId,addressLine1Txt,addressLine2Txt,
                                addressLine3Txt,cityTxt,stateTxt,Integer.parseInt(postcodeTxt));
                        boolean insertAddress = AddressDatabase.insertAddress(address);

                        if (insertAddress)
                        {
                            SnackbarCreator.createNewSnackbar(view,"Successfully sign up!");
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
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
        return !(usernameTxt.equals("")||emailTxt.equals("")||phoneTxt.equals("")||
                passwordTxt.equals("")|| postcodeTxt.equals("")||cityTxt.equals("")||
                addressLine1Txt.equals("")||(addressLine2.equals("")&&!addressLine3.equals("")));
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