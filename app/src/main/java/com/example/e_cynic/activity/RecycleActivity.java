package com.example.e_cynic.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.e_cynic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecycleActivity extends AppCompatActivity
{
    private ImageView example, uploadImg, pinLocation;
    private Button uploadBtn;

    // Request code
    private static final int SNAP_PHOTO = 0;
    private static final int CHOOSE_FROM_GALLERY = 1;
    private static final int PHOTO_PERMISSION = 2;
    private static final int LOCATION_PERMISSION = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

        example = findViewById(R.id.example);
        uploadImg = findViewById(R.id.uploadImg);
        uploadBtn = findViewById(R.id.uploadBtn);
        pinLocation = findViewById(R.id.pinLocation);

        bottomNavBar();

        uploadImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grantPermission();
                selectImg();
            }
        });

        pinLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //
            }
        });

        example.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(RecycleActivity.this, ElectronicAppliancesExampleActivity.class);
                startActivity(i);
            }
        });
    }

    private void grantPermission()
    {
        if (ContextCompat.checkSelfPermission(RecycleActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(RecycleActivity.this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, PHOTO_PERMISSION);
        }
    }

    private void selectImg()
    {
        final CharSequence [] options = {"Snap photo","Choose from gallery","Cancel"};
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Upload photo");

        ad.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if (options[i].equals("Snap photo"))
                {
                    Intent snapPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(snapPhoto,SNAP_PHOTO);
                }

                else if (options[i].equals("Choose from gallery"))
                {
                    Intent chooseFromGallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(chooseFromGallery,CHOOSE_FROM_GALLERY);
                }

                else if (options[i].equals("Cancel"))
                {
                    dialogInterface.dismiss();
                }
            }
        });

        ad.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED)
        {
            switch (requestCode)
            {
                case 1:
                    if (resultCode == RESULT_OK && data!= null)
                    {
                        Bitmap capturedImg = (Bitmap) data.getExtras().get("data");
                        uploadImg.setImageBitmap(capturedImg);
                    }
                    break;

                case 2:
                    if (resultCode == RESULT_OK && data != null)
                    {
                        Uri selectedImage = data.getData();
                        String [] filePath = {MediaStore.Images.Media.DATA};

                        if (selectedImage != null)
                        {
                            Cursor cursor = getContentResolver().query(selectedImage,filePath,null,null,null);

                            if (cursor != null)
                            {
                                cursor.moveToFirst();

                                int colInd = cursor.getColumnIndex(filePath[0]);
                                String imgPath = cursor.getString(colInd);
                                uploadImg.setImageBitmap(BitmapFactory.decodeFile(imgPath));
                                cursor.close();
                            }
                        }
                    }

                    break;
            }
        }
    }

    private void bottomNavBar()
    {
        // Initiate & assign variable
        BottomNavigationView btmNav = findViewById(R.id.btmNav);

        // Set selected layout
        btmNav.setSelectedItemId(R.id.recycle);

        // Perform item selected listener
        {
            btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
            {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item)
                {
                    switch (item.getItemId())
                    {
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