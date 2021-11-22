package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.Cursor;
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

    public boolean insertUser(User user)
    {
        ContentValues cv = new ContentValues();
        cv.put(uname,user.username);
        cv.put(email,user.email);
        cv.put(password,user.password);
        cv.put(name,user.name);
        cv.put(phoneNumber,user.phoneNumber);

        long result = db.insert(usersTable, null, cv);

        if (result == 1)
        {
            return false;
        }

        else
        {
            return true;
        }
    }

    public boolean checkUsername(String username)
    {
        Cursor c = db.rawQuery("select * from users where username = ?", new String[]{username});

        if (c.getCount()>0)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    //TODO maybe this got problem
    public int getUserId(String username)
    {
        int userId;
        Cursor c = db.rawQuery("select userId from users where username = '"+username+"';",null);

        if (c.moveToFirst())
        {
             userId = c.getInt(0);
             return userId;
        }

        else
        {
            return -1;
        }
    }
}
