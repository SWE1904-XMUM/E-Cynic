package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.entity.Address;

public class AddressDatabase
{
    public static final String addressesTable = "addresses";
    public static final String userId = "userId";
    public static final String firstLine = "firstLine";
    public static final String secondLine = "secondLine";
    public static final String thirdLine = "thirdLine";
    public static final String city = "city";
    public static final String state = "state";
    public static final String postcode = "postcode";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    public static void insertAddress(Address address)
    {
        ContentValues cv = new ContentValues();
        cv.put(userId,address.userId);
        cv.put(firstLine,address.firstLine);
        cv.put(secondLine,address.secondLine);
        cv.put(thirdLine,address.thirdLine);
        cv.put(city,address.city);
        cv.put(state,address.state);
        cv.put(postcode,address.postcode);

        long result = db.insert(addressesTable, null, cv);
    }
}
