package com.nick.controller;

import com.nick.model.User;
import com.nick.service.UserService;
import com.nick.web.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController implements Controller {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User create(User user) {
        return userService.create(user);
    }

    public User delete(User user) {
        return userService.delete(user);
    }

    public User update(User user) {
        return userService.update(user);
    }

    @Override
    public ViewModel process(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

}
