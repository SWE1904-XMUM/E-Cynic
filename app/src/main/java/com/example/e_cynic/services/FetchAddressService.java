package com.example.e_cynic.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class FetchAddressService extends IntentService
{
    public FetchAddressService()
    {
        super("FetchAddressService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {

    }
}
