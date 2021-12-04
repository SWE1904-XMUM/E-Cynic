package com.example.e_cynic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm:ss");

    public static String getCurrentDate()
    {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        return date;
    }

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
