package com.nick.service;

import com.nick.dao.UserDao;
import com.nick.model.User;
import com.nick.util.BaseUtil;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User delete(User user) {

        return userDao.delete(user);
    }

    @Override
    public Optional<String> checkUser(String email, String password) {
        User user = userDao.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            String token = BaseUtil.getUserToken();
            user.setToken(token);
            userDao.update(token);
            return Optional.of(token);
        }

        return Optional.empty();
    }
}
