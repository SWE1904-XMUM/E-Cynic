package com.example.e_cynic.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseConnectionProvider extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ecynic.db";
    private static DatabaseConnectionProvider connection_instance;
    private static SQLiteDatabase database;

    private DatabaseConnectionProvider(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DatabaseConnectionProvider getConnection(Context context) {
        if(connection_instance == null) {
            connection_instance = new DatabaseConnectionProvider(context.getApplicationContext(), DATABASE_NAME, null, 1);
        }
        return connection_instance;
    }

    public static SQLiteDatabase getDatabase(Context context) {
        if(database == null || !database.isOpen()) {
            database = getConnection(context).getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create databases
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
