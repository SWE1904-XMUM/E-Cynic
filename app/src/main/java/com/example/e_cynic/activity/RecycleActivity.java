package com.example.e_cynic.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.adapter.RecycleAddItemAdapter;
import com.example.e_cynic.constants.RequestCode;
import com.example.e_cynic.db.AddressDatabase;
import com.example.e_cynic.db.ItemDatabase;
import com.example.e_cynic.db.OrderDatabase;
import com.example.e_cynic.db.UserDatabase;
import com.example.e_cynic.entity.Address;
import com.example.e_cynic.entity.Item;
import com.example.e_cynic.entity.Order;
import com.example.e_cynic.session.SessionManager;
import com.example.e_cynic.utils.DateUtil;
import com.example.e_cynic.utils.ImageUtil;
import com.example.e_cynic.utils.userInteraction.SnackbarCreator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class RecycleActivity extends AppCompatActivity {
    // Views
    private ImageView example;
    private LinearLayout pinLocation;
    private Button submitRecycleBtn, addItem;
    private RecyclerView recycler_view;
    private RecycleAddItemAdapter rvAdapter;
    private SessionManager sessionManager;

    private EditText et_addLine1;
    private EditText et_addLine2;
    private EditText et_addLine3;
    private Spinner spinner_state;
    private EditText et_postcode;
    private EditText et_city;

    public ArrayList<Item> items;
    public Address address;

    public int clickedItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

        setViewComponent();
        bottomNavBar();
        RV_AddItem();
        sessionManager = new SessionManager(getApplicationContext());

        submitRecycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get userid
                //TODO get userId
//                String username = sessionManager.getUsername();
                String username = "testuser";
                Integer userId = UserDatabase.getUserIdByUsername(username);

                try {

                    //insert address to db
                    updateAddressFromRecycleForm(userId);
                    long addressId = AddressDatabase.insertAddressAndGetAddressId(address);
                    if (addressId <= 0) {
                        SnackbarCreator.createNewSnackbar(submitRecycleBtn, "Please try again");
                        return;
                    }

                    //create order and get orderid
                    long orderId = OrderDatabase.insertOrderAndGetOrderId(new Order(null, userId, (int) addressId,
                            DateUtil.getCurrentTimestamp(), null));
                    if (orderId <= 0) {
                        SnackbarCreator.createNewSnackbar(submitRecycleBtn, "Please try again");
                        return;
                    }

                    //insert items to order
                    for (Item item : items) {
                        item.orderId = (int) orderId;
                        boolean result = ItemDatabase.insertItem(item);
                        if (result == false) {
                            SnackbarCreator.createNewSnackbar(submitRecycleBtn, "Please try again");
                            return;
                        }
                    }


                    //direct to order detail activity, pass orderid
                    Intent i = new Intent(RecycleActivity.this, OrderDetailActivity.class);
                    i.putExtra("orderId", String.valueOf(orderId));
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pinLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecycleActivity.this, PinLocationActivity.class);
                startActivityForResult(i, RequestCode.PIN_LOCATION_ACTIVITY);
            }
        });

        example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecycleActivity.this, ElectronicAppliancesExampleActivity.class);
                startActivity(i);
            }
        });

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.state));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setAdapter(myAdapter);
    }


    private void RV_AddItem() {

        //add item recycler view
        addItem = findViewById(R.id.btn_addItem);
        items = new ArrayList<>();
        items.add(new Item());

        //set click
        addItem.setOnClickListener(new View.OnClickListener() {
            int i = 1;

            @Override
            public void onClick(View view) {
                i++;
                items.add(new Item());
                rvAdapter.notifyItemInserted(i);
            }
        });
        updateRecyclerView();
    }

    public void updateRecyclerView() {
        recycler_view = findViewById(R.id.enterItemDetail);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new RecycleAddItemAdapter(this, items, RecycleActivity.this);
        recycler_view.setAdapter(rvAdapter);
    }

    private void setViewComponent() {
        example = findViewById(R.id.example);
        submitRecycleBtn = findViewById(R.id.btn_submitRecycle);
        pinLocation = findViewById(R.id.pinLocation);

        et_addLine1 = findViewById(R.id.addressLine1);
        et_addLine2 = findViewById(R.id.addressLine2);
        et_addLine3 = findViewById(R.id.addressLine3);
        spinner_state = findViewById(R.id.spinner_state);
        et_postcode = findViewById(R.id.postcode);
        et_city = findViewById(R.id.city);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case RequestCode.SNAP_PHOTO:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap capturedImg = (Bitmap) data.getExtras().get("data");
                        items.get(clickedItem).image = ImageUtil.bitmapToByteArray(capturedImg);
                        updateRecyclerView();
                    }
                    break;

                case RequestCode.CHOOSE_FROM_GALLERY:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePath = {MediaStore.Images.Media.DATA};

                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage, filePath, null, null, null);

                            if (cursor != null) {
                                cursor.moveToFirst();

                                int colInd = cursor.getColumnIndex(filePath[0]);
                                String imgPath = cursor.getString(colInd);
                                items.get(clickedItem).image = ImageUtil.imagePathToByteArray(imgPath);
                                updateRecyclerView();
                                cursor.close();
                            }
                        }
                    }

                    break;

                case RequestCode.PIN_LOCATION_ACTIVITY:
                    if (resultCode == RESULT_OK && data != null) {
                        //TODO do something with the returned address
                        SessionManager sessionManager = new SessionManager(getApplicationContext());
                        String username = sessionManager.getUsername();
                        Integer userId = UserDatabase.getUserIdByUsername(username);
                        address = new Address(null,
                                userId,
                                data.getStringExtra("firstLine"),
                                data.getStringExtra("secondLine"),
                                null,
                                data.getStringExtra("city"),
                                data.getStringExtra("state"),
                                Integer.parseInt(data.getStringExtra("postcode")));
                        updateAddressFields();
                    }
                    break;
            }
        }
    }

    private void updateAddressFields() {
        et_addLine1.setText(address.firstLine);
        et_addLine2.setText(address.secondLine);
        et_addLine3.setText(address.thirdLine);
        spinner_state.setSelection(getStateIdInSpinner(address.state));
        et_postcode.setText(String.valueOf(address.postcode));
        et_city.setText(address.city);
    }

    private void updateAddressFromRecycleForm(Integer userId) {
        address.userId = userId;
        address.firstLine = et_addLine1.getText().toString();
        address.secondLine = et_addLine2.getText().toString();
        address.thirdLine = et_addLine3.getText().toString();
        address.state = spinner_state.getSelectedItem().toString();
        address.postcode = Integer.parseInt(et_postcode.getText().toString());
        address.city = et_city.getText().toString();
    }

    private int getStateIdInSpinner(String state) {
        for (int i = 0; i < spinner_state.getCount(); i++) {
            if(spinner_state.getItemAtPosition(i).equals(state)) {
                return i;
            }
        }
        return -1;
    }

    private void bottomNavBar() {
        // Initiate & assign variable
        BottomNavigationView btmNav = findViewById(R.id.btmNav);

        // Set selected layout
        btmNav.setSelectedItemId(R.id.recycle);

        // Perform item selected listener
        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.recycle:
                        return true;

                    case R.id.quiz:
                        startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}