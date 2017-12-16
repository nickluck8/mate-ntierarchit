package com.nick.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Reflection {

    public static String getTableName(Object o) {
        Class<?> cls = o.getClass();
        Table tableName = cls.getAnnotation(Table.class);
        return tableName.value();
    }

    public static <T> Map<String, String> getMap(T t) {
        Map map = new HashMap();


        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                try {
                    map.put(column.value(), field.get(t).toString());
                } catch (IllegalAccessException e) {
                    System.out.println("Exception getMap"); 
                }
            }
        }
        return map;
    }
}
