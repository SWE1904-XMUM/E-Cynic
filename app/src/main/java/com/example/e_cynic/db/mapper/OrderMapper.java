package com.example.e_cynic.db.mapper;

import android.database.Cursor;

import com.example.e_cynic.entity.Order;
import com.example.e_cynic.utils.mapper.FieldTypeCaster;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderMapper {
    public static Order mapCursorToOneOrder(Cursor cursor) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        if (cursor == null) {
            return null;
        }

        Order order = Order.class.getDeclaredConstructor().newInstance();

        List<String> column_names = Arrays.asList(cursor.getColumnNames());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Field field = order.getClass().getDeclaredField(column_names.get(i));
            field.set(order, FieldTypeCaster.parseValueToType(cursor.getString(i), field.getType()));
        }

        return order;
    }

    public static List<Order> mapCursorToOrders(Cursor cursor) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Order> orderList = new ArrayList<>();

        do {
            orderList.add(mapCursorToOneOrder(cursor));
        } while (cursor.moveToNext());

        return orderList;
    }

}
