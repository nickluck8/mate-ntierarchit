package com.nick.dao;

import com.nick.model.Column;
import com.nick.model.Table;
import com.nick.model.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

//    public UserDaoImpl() {
//        super();
//    }

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public User create(User user) {
        super.create(user);
        return null;
    }

    public User findById(Long id) {
        super.findById(id);
        return null;
    }

    public User update(User user) {
        super.update(user);
        return null;
    }

    public User delete(User user) {
        super.delete(user);
        return null;
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        try {
            String sql = "SELECT * FROM USERS WHERE EMAIL=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(String token) {
        return null;
    }

    public StringBuilder getAnnotations(User user) {
        StringBuilder resultSet = new StringBuilder();

        Class<?> c = user.getClass();
        Field[] fields = c.getDeclaredFields();
        // Map<String, String> map = null;
        Table t = c.getAnnotation(Table.class);
        System.out.println(t.value());
        for (Field f : fields) {
            Column cs = f.getAnnotation(Column.class);
            System.out.println(cs.value() + " " + f.getName() + " ");
            // map.put(cs.value(), f.getName());
            resultSet.append(cs.value() + " ");
//            map.put(cs.value(), user.getId().toString());
        }
        return resultSet;
    }

    @Override
    public User getUserByToken(String token) {
        User user = new User();
        try {
            String sql = "SELECT * FROM USERS WHERE TOKEN=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, token);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }
}
