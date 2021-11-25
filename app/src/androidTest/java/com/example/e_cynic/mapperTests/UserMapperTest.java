package com.example.e_cynic.mapperTests;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.db.mapper.UserMapper;
import com.example.e_cynic.entity.User;
import com.example.e_cynic.utils.DatabaseUtil;
import com.example.e_cynic.utils.LoggingUtil;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMapperTest {

    private SQLiteDatabase database = DatabaseUtil.getTestDatabase();

    private Integer userid  = 1;
    private String username = "testuser";
    private String email = "test@gmail.com";
    private String password = "testuser";
    private String phoneNumber = "12345";
    private User test_user = new User(userid, username, email, password, phoneNumber);

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

    @Test
    public void mapUserToContentValues() throws IllegalAccessException {
        ContentValues cv = UserMapper.mapUserToContentValues(test_user);
        LoggingUtil.printMessage("map user to content values", cv.toString());
    }

    //TODO test
    @Test
    public void mapCursorToOneUser() {
    }

    @Test
    public void mapCursorToUsers() {
    }
}
