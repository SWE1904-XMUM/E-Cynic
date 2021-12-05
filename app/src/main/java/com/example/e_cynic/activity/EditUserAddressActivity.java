package com.example.e_cynic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.telephony.TelephonyCallback;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_cynic.R;
import com.example.e_cynic.db.AddressDatabase;
import com.example.e_cynic.entity.Address;

public class EditUserAddressActivity extends AppCompatActivity {

    private Integer addressId;
    private Address address;

    private EditText et_address_line1;
    private EditText et_address_line2;
    private EditText et_address_line3;
    private EditText et_postcode;
    private EditText et_city;
    private Spinner spinner_state;

    private Button btn_editAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_address);
        setUpViewComponents();

        Intent intent = getIntent();
        addressId = Integer.valueOf(intent.getStringExtra("addressId"));
        try {
            address = AddressDatabase.getAddressByAddressId(addressId);
            updateAddressFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAddressFields() {
        et_address_line1.setText(address.firstLine);
        if (!address.secondLine.equals("") || address.secondLine != null) {
            et_address_line2.setText(address.secondLine);
        }
        if (!address.thirdLine.equals("") || address.thirdLine != null) {
            et_address_line3.setText(address.thirdLine);
        }
        et_postcode.setText(String.valueOf(address.postcode));
        et_city.setText(address.city);
        spinner_state.setSelection(getStateIdInSpinner(address.state));
    }

    private void setUpViewComponents() {
        et_address_line1 = findViewById(R.id.addressLine1);
        et_address_line2 = findViewById(R.id.addressLine2);
        et_address_line3 = findViewById(R.id.addressLine3);
        et_postcode = findViewById(R.id.postcode);
        et_city = findViewById(R.id.city);
        spinner_state = findViewById(R.id.state);

        btn_editAddress = findViewById(R.id.btn_editAddress);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(EditUserAddressActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.state));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setAdapter(myAdapter);

        btn_editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAddressFromFields();
                boolean result = false;
                try {
                    result = AddressDatabase.editAddressByAddressId(address);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                if(result == true) {
                    setResult(RESULT_OK, intent);
                }
                else {
                    setResult(RESULT_CANCELED, intent);
                }
                finish();
            }
        });

        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent();
                setResult(RESULT_CANCELED, i);
                finish();
            }
        });
    }

    private void updateAddressFromFields() {
        address.firstLine = et_address_line1.getText().toString();
        address.secondLine = et_address_line2.getText().toString();
        address.thirdLine = et_address_line3.getText().toString();
        address.postcode = Integer.valueOf(et_postcode.getText().toString());
        address.state = spinner_state.getSelectedItem().toString();
        address.city = et_city.getText().toString();
    }

    private int getStateIdInSpinner(String state) {
        for (int i = 0; i < spinner_state.getCount(); i++) {
            if (spinner_state.getItemAtPosition(i).equals(state)) {
                return i;
            }
        }
        return -1;
    }
}