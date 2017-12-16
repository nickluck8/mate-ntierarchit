package com.nick.controller;

import com.nick.web.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    ViewModel process(HttpServletRequest request, HttpServletResponse response);
}
