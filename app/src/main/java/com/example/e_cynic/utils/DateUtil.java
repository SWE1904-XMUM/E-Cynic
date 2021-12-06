package com.example.e_cynic.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    public static String  getDateFromTimestamp(Long timestamp)
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
    }

    public static String getDuration(String currentDate,String orderDate)
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Date d1 = format.parse(currentDate);
            Date d2 = format.parse(orderDate);
            long diff = d2.getTime() - d1.getTime();
            String duration = String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            System.out.println ("Days: " + duration);
            return duration;
        }

        catch (ParseException e)
        {
            e.printStackTrace();
            return "Error";
        }
    }
}
