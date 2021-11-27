package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.db.mapper.UserMapper;
import com.example.e_cynic.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase
{
    public static final String usersTable = "users";
    public static final String username = "username";
    public static final String email = "email";
    public static final String password = "password";
    public static final String phoneNumber = "phoneNumber";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    public static boolean verifyUser(String username, String password) {
        Cursor c = db.rawQuery("select password from users where username=?", new String[]{username});
        return (c.moveToNext() && password.equals(c.getString(0)));
    }

    public static boolean insertUser(User user) throws IllegalAccessException {
        ContentValues cv = UserMapper.mapUserToContentValues(user);
        long result = db.insert(usersTable, null, cv);
        return result > 0;
    }

    public static User getUserInfoByUsername(String username) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        //return User object in users table (except password)
        Cursor c = db.rawQuery("select userId, username, email, phoneNumber from users where username=?",
                new String[]{username});
        return (c.moveToNext()) ? UserMapper.mapCursorToOneUser(c) : null;
    }

    public static boolean checkUsernameExistence(String username)
    {
        Cursor c = db.rawQuery("select username from users where username = ?", new String[]{username});
        return (c.getCount() > 0);
    }

    public static int getUserIdByUsername(String username)
    {
        Cursor c = db.rawQuery("select userId from users where username=?", new String[]{username});
        return (c.moveToNext()) ? c.getInt(0) : -1;
    }

    public static List<User> getAllUsers() throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Cursor c = db.rawQuery("select userId, username, email, phoneNumber from users", null);
        List<User> userList = new ArrayList<>();
        if(c.getCount() > 0) {
            userList = UserMapper.mapCursorToUsers(c);
        }
        return userList;
    }

    public static boolean editUserByUserId(User new_user) throws IllegalAccessException {
        ContentValues cv = UserMapper.mapUserToContentValues(new_user);
        long result = db.update(usersTable, cv, "userId=?", new String[]{String.valueOf(new_user.userId)});
        return result > 0;
    }
}
