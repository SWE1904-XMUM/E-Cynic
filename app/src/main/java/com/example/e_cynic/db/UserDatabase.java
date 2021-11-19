package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.e_cynic.entity.User;

public class UserDatabase
{
    public static final String usersTable = "users";
    public static final String uname = "username";
    public static final String email = "email";
    public static final String password = "password";
    public static final String name = "name";
    public static final String phoneNumber = "phoneNumber";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    public static void insertUser(User user)
    {
        ContentValues cv = new ContentValues();
        cv.put(uname,user.username);
        cv.put(email,user.email);
        cv.put(password,user.password);
        cv.put(name,user.name);
        cv.put(phoneNumber,user.phoneNumber);

        long result = db.insert(usersTable, null, cv);

        //if (result)
    }
}
