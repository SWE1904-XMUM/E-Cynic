package com.example.e_cynic.utils.userInteraction;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogCreator extends UIMaker
{
    public AlertDialogCreator()
    {

    }

    public void createNewAlertDialog(Context context,String title,String message)
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setTitle(title);
        ad.setMessage(message);
    }
}
