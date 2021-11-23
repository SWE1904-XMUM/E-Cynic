package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.entity.Order;

public class OrderDatabase
{
    public static final String ordersTable = "orders";
    public static final String userId = "userId";
    public static final String addressId = "addressId";
    public static final String date = "date";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    public static boolean insertOrder(Order order)
    {
        ContentValues cv = new ContentValues();
        cv.put(userId,order.userId);
        cv.put(addressId,order.addressId);
        cv.put(date, String.valueOf(order.date));

        long result = db.insert(ordersTable, null, cv);

        if (result == 1)
        {
            return false;
        }

        else
        {
            return true;
        }
    }
}
