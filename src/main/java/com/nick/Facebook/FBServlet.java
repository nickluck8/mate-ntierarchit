package com.nick.Facebook;

import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        FacebookClient facebookClient = new DefaultFacebookClient(Constants.MY_ACCESS_TOKEN);
//
//        User user = facebookClient.fetchObject("me", User.class);
//        System.out.println("User = " + user);
//        System.out.println("USerName = " + user.getName());
//        System.out.println("Email = " + user.getEmail());
//
//        JsonObject userData = facebookClient.fetchObject("me", JsonObject.class, Parameter.with("fields", "name, first_name, email"));
//
//        System.out.println("\nUserData" + userData);
//        System.out.println("First Name = " + userData.getString("first_name"));
//        System.out.println("Last name = " + userData.getString("name"));
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);;

    }
}
