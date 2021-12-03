package com.example.e_cynic.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.adapter.RecycleAddItemAdapter;
import com.example.e_cynic.constants.RequestCode;
import com.example.e_cynic.entity.Item;
import com.example.e_cynic.utils.ImageUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {
    // Views
    private ImageView example, pinLocation;
    private Button uploadBtn, addItem;
    RecyclerView recycler_view;
    RecycleAddItemAdapter rvAdapter;
    ArrayList<Item> items;

    public int clickedItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

        setViewComponent();
        bottomNavBar();

        RV_AddItem();

        //test: redirect to order details page (working)
        uploadBtn = findViewById(R.id.uploadBtn);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecycleActivity.this, orderDetailActivity.class);
                startActivity(i);
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
    }


    private void RV_AddItem() {

        //add item recycler view
        addItem = findViewById(R.id.btn_addItem);
        items = new ArrayList<>();
        items.add(new Item("category", null));

        //set click
        addItem.setOnClickListener(new View.OnClickListener() {
            int i = 1;

            @Override
            public void onClick(View view) {
                i++;
                items.add(new Item("c2", null));
                rvAdapter.notifyItemInserted(i);
            }
        });
        updateRecyclerView();
    }

    private void updateRecyclerView() {
        recycler_view = findViewById(R.id.enterItemDetail);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new RecycleAddItemAdapter(this, items, RecycleActivity.this);
        recycler_view.setAdapter(rvAdapter);
    }

    private void setViewComponent() {
        example = findViewById(R.id.example);
        uploadBtn = findViewById(R.id.uploadBtn);
        pinLocation = findViewById(R.id.pinLocation);
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
                        /*
                        currentAddressValue.getFeatureName(); //No. ...
                        currentAddressValue.getThoroughfare(); // Jalan ...
                        currentAddressValue.getSubLocality(); // Taman/Kampung ...
                        currentAddressValue.getLocality(); // city
                        currentAddressValue.getPostalCode(); //postcode
                        currentAddressValue.getAdminArea(); //state
                        */
                        System.out.println(data.getStringExtra("address"));
                    }
                    break;
            }
        }
    }

    private void bottomNavBar() {
        // Initiate & assign variable
        BottomNavigationView btmNav = findViewById(R.id.btmNav);

        // Set selected layout
        btmNav.setSelectedItemId(R.id.recycle);

        // Perform item selected listener
        {
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
}