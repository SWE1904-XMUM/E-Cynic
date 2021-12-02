package com.example.e_cynic.db;

import android.database.sqlite.SQLiteDatabase;

public class PointsDatabase
{
    public static final String pointsTable = "points";
    public static final String userId = "userId";
    public static final String pointsEarned = "pointsEarned";
    public static final String date = "date";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    //TODO PointsDatabase function
}
