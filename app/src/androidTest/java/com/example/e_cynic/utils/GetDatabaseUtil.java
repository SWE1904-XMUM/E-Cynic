package com.example.e_cynic.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.e_cynic.db.DatabaseConnectionProvider;

public class GetDatabaseUtil {

    private static Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private static SQLiteDatabase database;

    public static SQLiteDatabase getTestDatabase() {
        if(database == null) {
            synchronized (GetDatabaseUtil.class) {
                database = DatabaseConnectionProvider.getDatabase(context);
            }
        }
        return database;
    }
}
