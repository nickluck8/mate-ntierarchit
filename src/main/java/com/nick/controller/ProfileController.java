package com.nick.controller;

import com.nick.web.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileController implements Controller {
    @Override
    public ViewModel process(HttpServletRequest req, HttpServletResponse resp) {
        ViewModel viewModel = new ViewModel("profile");


        return viewModel;
    }
}
