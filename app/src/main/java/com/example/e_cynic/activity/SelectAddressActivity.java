package com.example.e_cynic.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.adapter.AddressListAdapter;
import com.example.e_cynic.db.AddressDatabase;
import com.example.e_cynic.db.UserDatabase;
import com.example.e_cynic.entity.Address;
import com.example.e_cynic.session.SessionManager;

import java.util.List;

public class SelectAddressActivity extends AppCompatActivity {

    private LinearLayout ll_user_address;
    private LinearLayout ll_no_user_address;
    private RecyclerView rv_addresses;
    private List<Address> addressList;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);

        sessionManager = new SessionManager(SelectAddressActivity.this);

        try {
            //TODO get userid
//            addressList = AddressDatabase.getAddressesByUserId(UserDatabase.getUserIdByUsername(sessionManager.getUsername()));
            addressList = AddressDatabase.getAddressesByUserId(UserDatabase.getUserIdByUsername("testuser"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setUpLinearLayout();

        if (addressList != null) {
            ll_user_address.setVisibility(View.VISIBLE);
            ll_no_user_address.setVisibility(View.GONE);
            setUpRecyclerView();
        } else {
            ll_user_address.setVisibility(View.GONE);
            ll_no_user_address.setVisibility(View.VISIBLE);
        }
    }

    private void setUpLinearLayout() {
        ll_user_address = findViewById(R.id.ll_user_address);
        ll_no_user_address = findViewById(R.id.ll_no_user_address);
    }

    private void setUpRecyclerView() {
        rv_addresses = findViewById(R.id.rv_addresses);
        AddressListAdapter adapter = new AddressListAdapter(this, addressList);
        rv_addresses.setAdapter(adapter);
        rv_addresses.setLayoutManager(new LinearLayoutManager(this));
    }
}