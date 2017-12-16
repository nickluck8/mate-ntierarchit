package com.nick.controller;

import com.nick.service.UserService;
import com.nick.web.ViewModel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.nick.util.ApplicationConstants.TOKEN;
import static com.nick.web.Methods.GET;

public class LoginUserController implements Controller {

    private UserService userService;

    public LoginUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(HttpServletRequest req, HttpServletResponse resp) {
        ViewModel vm = new ViewModel("login");
        if (req.getMethod().equals(GET.toString())) {
            return vm;
        }
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String token = userService.checkUser(email, password).orElse("unauthorized");
        resp.addCookie(new Cookie(TOKEN, token));
        return vm;
    }
}
