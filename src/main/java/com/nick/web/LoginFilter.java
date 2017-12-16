package com.nick.web;

import com.nick.Factory;
import com.nick.dao.UserDao;
import com.nick.dao.UserDaoImpl;
import com.nick.model.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.nick.util.ApplicationConstants.TOKEN;

public class LoginFilter implements Filter {
    private UserDao userDao;
    private final String protectedUri = "/servlet/profile";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = new UserDaoImpl(Factory.getConnection());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (httpServletRequest.getRequestURI().equals(protectedUri)) {
            String token = null;
            for (Cookie cookie : cookies) {
                String name = cookie.getName().toUpperCase();
                if (name.equals(TOKEN)) {
                    token = cookie.getValue();
                    User user = userDao.getUserByToken(token);
                    httpServletRequest.setAttribute("UserId", String.valueOf(user.getId()));
                }
            }
            if (token == null) {
               // httpServletResponse.sendRedirect("/servlet/login");
                httpServletRequest.getRequestDispatcher("/servlet/login").forward(httpServletRequest, servletResponse);
            }
        }
        filterChain.doFilter(httpServletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
