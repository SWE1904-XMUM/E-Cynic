package com.example.e_cynic.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_cynic.db.mapper.AddressMapper;
import com.example.e_cynic.entity.Address;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AddressDatabase
{
    public static final String addressesTable = "addresses";
    public static final String userId = "userId";
    public static final String firstLine = "firstLine";
    public static final String secondLine = "secondLine";
    public static final String thirdLine = "thirdLine";
    public static final String city = "city";
    public static final String state = "state";
    public static final String postcode = "postcode";

    private static SQLiteDatabase db = DatabaseConnectionProvider.getDatabase(null);

    public static boolean insertAddress(Address address)
    {
        ContentValues cv = new ContentValues();
        cv.put(userId,address.userId);
        cv.put(firstLine,address.firstLine);
        cv.put(secondLine,address.secondLine);
        cv.put(thirdLine,address.thirdLine);
        cv.put(city,address.city);
        cv.put(state,address.state);
        cv.put(postcode,address.postcode);

        long result = db.insert(addressesTable, null, cv);
        return result > 0;
    }

    public static Address getAddressByAddressId(Integer addressId) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Cursor c = db.rawQuery("select * from addresses where addressId=?",
                new String[]{String.valueOf(addressId)});
        return (c.moveToNext()) ? AddressMapper.mapCursorToOneAddress(c) : null;
    }

    public static List<Address> getAddressesByUserId(Integer userId) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Cursor c = db.rawQuery("select * from addresses where userId=?",
                new String[]{String.valueOf(userId)});
        return (c.moveToNext()) ? AddressMapper.mapCursorToAddress(c) : null;
    }

    public static List<Address> getAddressesByUsername(String username) throws NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Integer userId = UserDatabase.getUserIdByUsername(username);
        return getAddressesByUserId(userId);
    }

    public static boolean removeAddressByAddressId(Integer addressId) {
        long result = db.delete(addressesTable, "addressId=?", new String[]{String.valueOf(addressId)});
        return result > 0;
    }

    public static boolean editAddressByAddressId(Integer addressId, Address new_address) throws IllegalAccessException {
        ContentValues cv = AddressMapper.mapAddressToContentValues(new_address);
        db.update(addressesTable, cv, "addressId=?", new String[]{String.valueOf(addressId)});
        return false;
    }
}
