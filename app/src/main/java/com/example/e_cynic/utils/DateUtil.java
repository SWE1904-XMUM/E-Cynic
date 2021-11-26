package com.example.e_cynic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    //TODO DateUtil
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm:ss");

    public static String getCurrentDateTime() {
        return dateFormat.format(new Date().getTime());
    }

    public static String getDateTimeByTimestamp(Long timestamp) {
        return dateFormat.format(new Date(timestamp).getTime());
    }

    public static Long getCurrentTimestamp() {
        return new Date().getTime();
    }
}
