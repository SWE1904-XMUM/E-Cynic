package com.example.e_cynic.db.mapper;

import android.database.Cursor;

import com.example.e_cynic.entity.Item;
import com.example.e_cynic.utils.mapper.FieldTypeCaster;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemMapper {
    public static Item mapCursorToOneItem(Cursor cursor) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        if (cursor == null) {
            return null;
        }

        Item item = Item.class.getDeclaredConstructor().newInstance();

        List<String> column_names = Arrays.asList(cursor.getColumnNames());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Field field = item.getClass().getDeclaredField(column_names.get(i));
            field.set(item, FieldTypeCaster.parseValueToType(cursor.getString(i), field.getType()));
        }

        return item;
    }

    public static List<Item> mapCursorToItems(Cursor cursor) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Item> itemList = new ArrayList<>();

        do {
            itemList.add(mapCursorToOneItem(cursor));
        } while (cursor.moveToNext());

        return itemList;
    }

}
