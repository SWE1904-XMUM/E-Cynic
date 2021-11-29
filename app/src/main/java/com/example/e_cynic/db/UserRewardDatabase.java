package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.db.mapper.UserRewardMapper;
import com.example.e_cynic.entity.UserReward;

import java.lang.reflect.InvocationTargetException;

public class UserRewardDatabase {
    public static final String userRewardsTable = "userRewards";
    public static final String rewardId = "rewardId";
    public static final String userId = "userId";
    public static final String date = "date";
    public static final String rewardItem = "rewardItem,";
    public static final String points = "points";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    public static boolean insertUserReward(UserReward userReward) throws IllegalAccessException {
        ContentValues cv = UserRewardMapper.mapUserRewardToContentValues(userReward);
        long result = db.insert(userRewardsTable, null, cv);
        return result > 0;
    }

    public static UserReward getUserRewardInfoByUsername(String username) throws InvocationTargetException,
            NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Integer userId = UserDatabase.getUserIdByUsername(username);
        return getUserRewardByUserId(userId);
    }

    public static UserReward getUserRewardByUserId(Integer userId) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Cursor c = db.rawQuery("select * from userRewards where userid=?", new String[]{String.valueOf(userId)});
        return (c.moveToNext()) ? UserRewardMapper.mapCursorToOneUserReward(c) : null;
    }
}
