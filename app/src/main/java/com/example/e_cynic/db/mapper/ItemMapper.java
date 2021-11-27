package com.example.e_cynic.db.mapper;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.e_cynic.entity.Item;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ItemMapper {
    public static Item mapCursorToOneItem(Cursor cursor) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, IOException {
        return (cursor.moveToFirst()) ? Mapper.mapCursorToOne(cursor, Item.class) : null;
    }

    public static List<Item> mapCursorToItems(Cursor cursor) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
        return (cursor.moveToFirst()) ? Mapper.mapCursorToMany(cursor, Item.class) : null;
    }

    public static ContentValues mapItemToContentValues(Item item) throws IllegalAccessException, NoSuchMethodException {
        return Mapper.mapEntityToContentValues(item);
    }
}
