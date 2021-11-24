package com.example.e_cynic.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PhotoPermission
{
    private static final int PHOTO_PERMISSION = 2;

    public PhotoPermission()
    {

    }

    public void grantPhotoPermission(Context context)
    {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions((Activity) context, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, PHOTO_PERMISSION);
        }
    }
}
