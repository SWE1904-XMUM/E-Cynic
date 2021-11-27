package com.example.e_cynic.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectionProvider extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ecynic.db";
    private static DatabaseConnectionProvider connection_instance;
    private static SQLiteDatabase database;

    private DatabaseConnectionProvider(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DatabaseConnectionProvider getConnection(Context context) {
        if (connection_instance == null) {
            connection_instance = new DatabaseConnectionProvider(context.getApplicationContext(), DATABASE_NAME, null, 1);
        }
        return connection_instance;
    }

    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = getConnection(context).getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create users table
        sqLiteDatabase.execSQL("create table users (userId integer primary key autoincrement, username text unique, email text unique, password text, phoneNumber text)");

        //create addresses table
        sqLiteDatabase.execSQL("create table addresses (addressId integer primary key autoincrement, userId" +
                " integer, firstLine text not null, secondLine text, thirdLine text, city text not null, state text not null, postcode integer not null)");

        //create articles database
        sqLiteDatabase.execSQL("create table articles (articleId integer primary key autoincrement, articleName text, url text, articleDate text)");

        //create items database
        sqLiteDatabase.execSQL("create table items (itemId integer primary key autoincrement, orderId integer not null, itemName text not null, numberOfItems integer not null, image longblob not null, price number)");

        //create orders database
        sqLiteDatabase.execSQL("create table orders (orderId integer primary key autoincrement, userId integer not null, addressId integer not null, date text not null)");

        //create points database
        sqLiteDatabase.execSQL("create table points (pointId integer primary key autoincrement, userId integer not null, pointsEarned integer not null, date text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        List<String> databases_name = new ArrayList<>();
        databases_name.add("users");
        databases_name.add("addresses");
        databases_name.add("articles");
        databases_name.add("items");
        databases_name.add("orders");
        databases_name.add("points");
        for (int index = 0; index < databases_name.size(); index++) {
            sqLiteDatabase.execSQL("drop table if exists " + databases_name.get(index));
        }
        onCreate(sqLiteDatabase);
    }
}
