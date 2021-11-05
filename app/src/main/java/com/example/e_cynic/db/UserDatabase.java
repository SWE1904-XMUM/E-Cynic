package com.example.e_cynic.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class UserDatabase extends DatabaseProvider
{
    public UserDatabase(@Nullable Context context)
    {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        super.onUpgrade(db, i, i1);
    }
}
