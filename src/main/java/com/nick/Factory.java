package com.nick;

import com.nick.controller.UserController;
import com.nick.dao.UserDao;
import com.nick.dao.UserDaoImpl;
import com.nick.service.UserService;
import com.nick.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

public class Factory {

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:h2:tcp://localhost/~/test",
                    "sa",
                    ""
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static UserDao getUserDao(Connection connection) {
        return new UserDaoImpl(connection);
    }

    public static UserService getUserService(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

    public static UserController doSomething(UserService userService) {
        return new UserController(userService);
    }

    public static <T, R> Function<T, R> getSomething(Function<T, R> function) {
        return function;
    }
}
