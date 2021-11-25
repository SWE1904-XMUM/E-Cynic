package com.example.e_cynic.utils.mapper;

import android.content.ContentValues;

public class FieldTypeCaster {
    public static Object parseValueToType(String value, Class<?> type) {
        if(type == java.lang.Integer.class) {
            return Integer.parseInt(value);
        }
        else if(type == java.lang.Double.class) {
            return Double.parseDouble(value);
        }
        else if(type == java.lang.Float.class) {
            return Float.parseFloat(value);
        }
        else if(type == java.lang.Long.class) {
            return Long.valueOf(value);
        }
        else if(type == java.lang.String.class) {
            return String.valueOf(value);
        }
        else if(type == java.lang.Boolean.class) {
            return Boolean.valueOf(value);
        }
        else {
            return type.cast(value);
        }
    }
}
