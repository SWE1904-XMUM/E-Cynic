package com.example.e_cynic.utils.mapper;

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
        else {
            return type.cast(value);
        }
    }
}
