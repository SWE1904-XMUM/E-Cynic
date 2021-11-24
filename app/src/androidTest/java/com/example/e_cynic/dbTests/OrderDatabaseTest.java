package com.example.e_cynic.dbTests;

import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.db.OrderDatabase;
import com.example.e_cynic.entity.Order;
import com.example.e_cynic.utils.DatabaseUtil;
import com.example.e_cynic.utils.LoggingUtil;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class OrderDatabaseTest {

    private static SQLiteDatabase database = DatabaseUtil.getTestDatabase();

    private String username = "testuser";
    private Integer orderId = 1;
    private Integer userId = 3;

    @Test
    public void insertOrder() {
        Order order = new Order(null, userId,1, new Date().getTime());
        boolean result = OrderDatabase.insertOrder(order);
        LoggingUtil.printMessage("insert order", (result == true) ? "true" : "false");
    }

    @Test
    public void getOrdersByUsername() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
       List<Order> orderList = OrderDatabase.getOrdersByUsername(username);
        if(orderList == null) {
            LoggingUtil.printMessage("get orders by username", "no orders exist");
            return;
        }
        for (Order o :
                orderList) {
            LoggingUtil.printMessage("get orders by username", o.toString());
        }
    }

    @Test
    public void getOrderByOrderId() throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Order order = OrderDatabase.getOrderByOrderId(orderId);
        LoggingUtil.printMessage("get order by orderId", (order != null) ? order.toString() : "null");
    }

    @Test
    public void getOrderByUserId() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Order> orderList = OrderDatabase.getOrdersByUserId(userId);
        if(orderList == null) {
            LoggingUtil.printMessage("get order by userid", "no orders exist");
            return;
        }
        for (Order o :
                orderList) {
            LoggingUtil.printMessage("get order by userid", o.toString());
        }
    }
}