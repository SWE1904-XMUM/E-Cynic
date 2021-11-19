package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.entity.Item;

import java.sql.Blob;

public class ItemDatabase
{
    public static final String itemsTable = "items";
    public static final String orderId = "orderId";
    public static final String itemName = "itemName";
    public static final String numberOfItems = "numberOfItems";
    //TODO image -> byte
    //public static final Blob image = null;
    public static final String price = "price";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    public static void insertItem(Item item)
    {
        ContentValues cv = new ContentValues();
        cv.put(orderId,item.orderId);
        cv.put(itemName,item.itemName);
        cv.put(numberOfItems,item.numberOfItems);
        //cv.put(image);
        cv.put(price,item.price);

        long result = db.insert(itemsTable, null, cv);
    }
}
