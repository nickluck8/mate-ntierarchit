package com.nick.controller;


import com.nick.model.User;
import com.nick.service.UserService;
import com.nick.web.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.nick.web.Methods.GET;

public class CreateUserController implements Controller {


    private UserService userService;

    public CreateUserController(UserService userService) {
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

//    public User findById(User user) {
//        return userService.findById(user);
//    }

    @Override
    public ViewModel process(HttpServletRequest req, HttpServletResponse resp) {

        ViewModel viewModel = new ViewModel("signup");
        if (req.getMethod().equals(GET.toString())) {
            return viewModel;
        }
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.create(new User(name, email, password));

        return viewModel;

    }
}
