package com.example.e_cynic.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    TextView username,email,phone,password,
                     postcode,city,addressLine1,addressLine2,addressLine3;
    Spinner state;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // view
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

        Spinner mySpinner = (Spinner) findViewById(R.id.state);

        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(SignUpActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.state));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        signUpBtn.setOnClickListener(new View.OnClickListener()
        {
            UserDatabase userDatabase = new UserDatabase();
            AddressDatabase addressDatabase= new AddressDatabase();
            SnackbarCreator sb = new SnackbarCreator();

            @Override
            public void onClick(View view)
            {
                // text of view
                String usernameTxt = username.getText().toString();
                String emailTxt = email.getText().toString();
                String phoneTxt = phone.getText().toString();
                String passwordTxt = password.getText().toString();
                String postcodeTxt = postcode.getText().toString();
                String cityTxt = city.getText().toString();
                String addressLine1Txt = addressLine1.getText().toString();
                String addressLine2Txt = addressLine2.getText().toString();
                String addressLine3Txt = addressLine3.getText().toString();
                //TODO this line will cause error when open signUp page
                //String stateTxt = state.getSelectedItem().toString();

                if (usernameTxt.equals("")||emailTxt.equals("")||phoneTxt.equals("")||passwordTxt.equals("")||
                    postcodeTxt.equals("")||cityTxt.equals("")||addressLine1Txt.equals(""))
                {
                    sb.createNewSnackbar(view,"Please fill in blank field.");
                }

                else
                {
                    if(userDatabase.checkUsername(usernameTxt)==true)
                    {
                        sb.createNewSnackbar(view,"Username already exist, please try another one.");
                    }

                    else
                    {
                        User user = new User(null,usernameTxt,emailTxt,passwordTxt,phoneTxt);
                        boolean insertUser = userDatabase.insertUser(user);
                        user.toString();

                        if (insertUser)
                        {
                            int userId = userDatabase.getUserId(usernameTxt);
                            Address address = new Address(null,userId,addressLine1Txt,addressLine2Txt,addressLine3Txt,cityTxt,"Melaka",Integer.parseInt(postcodeTxt));
                            boolean insertAddress = addressDatabase.insertAddress(address);

                            if (insertAddress)
                            {
                                sb.createNewSnackbar(view,"Successfully sign up!");
                            }
                        }
                    }
                }
            }
        });
    }
}