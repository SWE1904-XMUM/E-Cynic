package com.example.e_cynic.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.adapter.RecycleAddItemAdapter;
import com.example.e_cynic.constants.RequestCode;
import com.example.e_cynic.permission.Permissions;
import com.example.e_cynic.utils.ImageUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {
    // Views
    private ImageView example, pinLocation;
    //private ImageView uploadImg;
    private Button uploadBtn, addItem;
    RecyclerView recycler_view;
    RecycleAddItemAdapter rvAdapter;
    ArrayList<String> items;


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

       /* uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Permissions permissions = new Permissions();
                    permissions.grantPhotoPermission(RecycleActivity.this);
                    selectImg();
                } else {
                    selectImg();
                }

            }
        });*/

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
        items.add("Item 1");

        //set click
        addItem.setOnClickListener(new View.OnClickListener() {
            int i = 1;

            @Override
            public void onClick(View view) {
                i++;
                items.add("Item " + i);
                rvAdapter.notifyItemInserted(i);
            }
        });

        recycler_view = findViewById(R.id.enterItemDetail);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new RecycleAddItemAdapter(this, items);
        recycler_view.setAdapter(rvAdapter);
    }

    private void setViewComponent() {
        example = findViewById(R.id.example);
        //uploadImg = findViewById(R.id.uploadImg);
        uploadBtn = findViewById(R.id.uploadBtn);
        pinLocation = findViewById(R.id.pinLocation);
    }

    private void selectImg() {
        final CharSequence[] options = {"Snap photo", "Choose from gallery", "Cancel"};
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Upload photo");

        ad.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (options[i].equals("Snap photo")) {
                    Intent snapPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(snapPhoto, RequestCode.SNAP_PHOTO);
                } else if (options[i].equals("Choose from gallery")) {
                    Intent chooseFromGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(chooseFromGallery, RequestCode.CHOOSE_FROM_GALLERY);
                } else if (options[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });

        ad.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case RequestCode.SNAP_PHOTO:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap capturedImg = (Bitmap) data.getExtras().get("data");
                        //uploadImg.setImageBitmap(capturedImg);
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
                                //uploadImg.setImageBitmap(ImageUtils.imagePathToBitmap(imgPath));
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