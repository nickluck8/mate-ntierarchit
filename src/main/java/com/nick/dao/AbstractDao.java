package com.nick.dao;

import com.nick.model.Column;
import com.nick.model.ID;
import com.nick.model.Reflection;
import com.nick.model.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public abstract class AbstractDao<T> implements GenericDao<T> {
    protected Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public AbstractDao() {

    }
    //crud implementation

    public T create(T t) {
        String sql = "INSERT INTO " + Reflection.getTableName(t) + " (" + getTableName(Reflection.getMap(t).keySet()) + ") VALUES ("
                + getNumQuestions(Reflection.getMap(t).size()) + ");";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int count = 1;
            for (Map.Entry<String, String> entry : Reflection.getMap(t).entrySet()) {
                preparedStatement.setString(count, entry.getValue());

                count++;
            }
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Exception create T");
        }


        return null;
    }

    public T findById(Long id) {
        return null;
    }

//    public T update(T t) {
//        String sql = "Update " + Reflection.getTableName(t) + " SET " + getTableName(Reflection.getMap(t).keySet())
//                + " WHERE ID = " + getId(t) + ";";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            int count = 1;
//            for (Map.Entry<String, String> entry : Reflection.getMap(t).entrySet()) {
//                preparedStatement.setString(count, entry.getValue());
//
//                count++;
//            }
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public T update(T t) {
        String sql = null;
        PreparedStatement statement = null;
        try {
            sql = getSql(t);
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getSql(T t) throws Exception {

        String tableName = t.getClass().getAnnotation(Table.class).value();
        String id = getId(t);
        String values = processValues(getValues(t));

        return "UPDATE " +
                tableName +
                " SET " +
                values +
                " WHERE ID = " +
                id;

    }

    private String processValues(Map<String, String> values) {
        return values.entrySet().stream()
                .map(k -> String.format("%s='%s'", k.getKey(), k.getValue()))
                .collect(Collectors.joining(","));

    }

    private String getId(T t) {
        for (Field f : t.getClass().getDeclaredFields()) {
            ID id = f.getAnnotation(ID.class);
            if (id != null) {
                f.setAccessible(true);
                try {
                    return f.get(t).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    private Map<String, String> getValues(T t) {
        Map<String, String> result = new HashMap<>();

        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                try {
                    result.put(column.value(), field.get(t).toString());
                } catch (IllegalAccessException e) {
                    System.out.println("Exception getMap");
                }
            }
        }
        return result;

    }

    public T delete(T t) {
        String sql = "DELETE FROM " + Reflection.getTableName(t) + " WHERE ID= " + getId(t);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    private static String getTableName(Set<String> strings) {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (String s :
                strings) {
            stringJoiner.add(s);
        }
        return stringJoiner.toString();
    }


    private static String getNumQuestions(int size) {
        StringJoiner sj = new StringJoiner(",");
        for (int i = 0; i < size; i++) {
            sj.add("?");
        }
        return sj.toString();
    }

}
