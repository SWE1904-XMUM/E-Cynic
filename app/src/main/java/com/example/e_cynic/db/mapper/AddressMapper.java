package com.example.e_cynic.db.mapper;

import android.database.Cursor;

import com.example.e_cynic.entity.Address;
import com.example.e_cynic.utils.mapper.FieldTypeCaster;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressMapper {
    public static Address mapCursorToOneAddress(Cursor cursor) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        if (cursor == null) {
            return null;
        }

        Address address = Address.class.getDeclaredConstructor().newInstance();

        List<String> column_names = Arrays.asList(cursor.getColumnNames());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Field field = address.getClass().getDeclaredField(column_names.get(i));
            field.set(address, FieldTypeCaster.parseValueToType(cursor.getString(i), field.getType()));
        }

        return address;
    }

    public static List<Address> mapCursorToAddresss(Cursor cursor) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Address> addressList = new ArrayList<>();

        do {
            addressList.add(mapCursorToOneAddress(cursor));
        } while (cursor.moveToNext());

        return addressList;
    }

}
