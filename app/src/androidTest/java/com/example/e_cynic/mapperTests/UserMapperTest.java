package com.example.e_cynic.mapperTests;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.db.mapper.UserMapper;
import com.example.e_cynic.entity.User;
import com.example.e_cynic.utils.DatabaseUtil;
import com.example.e_cynic.utils.LoggingUtil;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class UserMapperTest {

    SQLiteDatabase database = DatabaseUtil.getTestDatabase();

    @Test
    public void mapToOneUser_test() throws InvocationTargetException, NoSuchMethodException,
            NoSuchFieldException, InstantiationException, IllegalAccessException {
        Cursor cursor = database.rawQuery("select * from users limit 1", null);
        User user = null;
        if (cursor.moveToNext()) {
            user = UserMapper.mapCursorToOneUser(cursor);
        }
        LoggingUtil.printMessage("map to one user", user.toString());
    }

    @Test
    public void mapToManyUsers_test() throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Cursor cursor = database.rawQuery("select * from users", null);
        List<User> userList = new ArrayList<>();
        while (cursor.moveToNext()) {
            userList.add(UserMapper.mapCursorToOneUser(cursor));
        }

        for (User u : userList) {
            LoggingUtil.printMessage("map to many users", u.toString());
        }
    }
}
