package com.example.e_cynic.db.mapper;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.e_cynic.utils.mapper.ContentValuesMapper;
import com.example.e_cynic.utils.mapper.FieldTypeCaster;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mapper
{
    public static <T> T mapCursorToOne(Cursor cursor, Class<T> targetClass) throws NoSuchMethodException,
            IllegalAccessException
            , InvocationTargetException, InstantiationException, NoSuchFieldException {
        if(cursor == null) {
            return null;
        }

        Constructor cons = targetClass.getDeclaredConstructor();

        if(cons == null) {
            return null;
        }
        T object = (T) cons.newInstance();

        List<String> column_names = Arrays.asList(cursor.getColumnNames());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Field field = object.getClass().getDeclaredField(column_names.get(i));
            field.set(object, FieldTypeCaster.parseValueToType(cursor.getString(i), field.getType()));
        }

        return object;
    }

    public static <T> List<T> mapCursorToMany(Cursor cursor, Class<T> targetClass) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<T> objectList = new ArrayList<>();

        do {
            objectList.add(mapCursorToOne(cursor, targetClass));
        } while(cursor.moveToNext());

        return objectList;
    }

    public static <T> ContentValues mapEntityToContentValues(T target) throws IllegalAccessException {
        List<Field> field_list = Arrays.asList(target.getClass().getDeclaredFields());
        ContentValues cv = ContentValuesMapper.mapFieldsToContentValues(field_list, target);
        return cv;
    }
}
