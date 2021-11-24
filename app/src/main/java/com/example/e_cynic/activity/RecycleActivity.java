package com.example.e_cynic.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.e_cynic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class RecycleActivity extends AppCompatActivity
{
    private ImageView example, uploadImg;
    private Button uploadBtn;
    private String img = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

        example = findViewById(R.id.example);
        uploadImg = findViewById(R.id.uploadImg);
        uploadBtn = findViewById(R.id.uploadBtn);

        bottomNavBar();

        uploadImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                selectImg();
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
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory(),"img.filetype");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent,1);
                }

                else if (options[i].equals("Choose from gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                }

                else if (options[i].equals("Cancel"))
                {
                    dialogInterface.dismiss();
                }
            }
        });

        ad.show();
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