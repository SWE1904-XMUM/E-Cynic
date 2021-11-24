package com.example.e_cynic.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LocationPermission
{
    // TODO Request code
    private static final int LOCATION_PERMISSION = 3;

    public LocationPermission()
    {

    }

    public void grantLocationPermission(Context context)
    {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions((Activity) context, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, RequestCode.LOCATION_PERMISSION);
        }
    }
}
