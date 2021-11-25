package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.db.mapper.OrderMapper;
import com.example.e_cynic.entity.Order;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class OrderDatabase
{
    public static final String ordersTable = "orders";
    public static final String orderId = "orderId";
    public static final String userId = "userId";
    public static final String addressId = "addressId";
    public static final String date = "date";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    public static boolean insertOrder(Order order)
    {
        ContentValues cv = new ContentValues();
        cv.put(userId,order.userId);
        cv.put(addressId,order.addressId);
        cv.put(date, String.valueOf(order.date));

        long result = db.insert(ordersTable, null, cv);
        return result > 0;
    }

    public static List<Order> getOrdersByUsername(String username) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Integer userId = UserDatabase.getUserIdByUsername(username);
        Cursor c = db.rawQuery("select * from orders where userId=?", new String[]{String.valueOf(userId)});
        return (c.moveToNext()) ? OrderMapper.mapCursorToOrders(c) : null;
    }

    public static Order getOrderByOrderId(int orderId) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Cursor c = db.rawQuery("select * from orders where orderId=?",
                new String[]{String.valueOf(orderId)});
        return (c.moveToNext()) ? OrderMapper.mapCursorToOneOrder(c) : null;
    }

    public static List<Order> getOrdersByUserId(int userId) throws NoSuchMethodException,
            NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Cursor c = db.rawQuery("select * from orders where userId=?",null);
        return (c.moveToNext()) ? OrderMapper.mapCursorToOrders(c) : null ;
    }

    public static boolean editOrderByOrderId(Integer orderId, Order order) {
        //TODO Map Order to ContentValues
        return false;
    }
}
