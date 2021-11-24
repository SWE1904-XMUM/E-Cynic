package com.example.e_cynic.db.mapper;

import android.database.Cursor;

import com.example.e_cynic.entity.User;
import com.example.e_cynic.utils.mapper.FieldTypeCaster;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMapper
{
    public static User mapCursorToOneUser(Cursor cursor) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        if(cursor == null) {
            return null;
        }

        User user = User.class.getDeclaredConstructor().newInstance();

        List<String> column_names = Arrays.asList(cursor.getColumnNames());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Field field = user.getClass().getDeclaredField(column_names.get(i));
            field.set(user, FieldTypeCaster.parseValueToType(cursor.getString(i), field.getType()));
        }

        return user;
    }

    public static List<User> mapCursorToUsers(Cursor cursor) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<User> userList = new ArrayList<>();

        do {
            userList.add(mapCursorToOneUser(cursor));
        } while(cursor.moveToNext());

        return userList;
    }
}
