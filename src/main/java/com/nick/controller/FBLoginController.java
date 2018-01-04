package com.nick.controller;

import com.nick.model.User;
import com.nick.service.UserService;
import com.nick.web.ViewModel;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.nick.web.Methods.GET;

public class FBLoginController implements Controller {
    public final String MY_APP_ID = "218978205312420";
    public final String MY_APP_SECRET = "802d18e3bf4b9cf436d8798115c0f058";
    public final String MY_ACCESS_TOKEN = "EAACEdEose0cBAOuvZAhaMZCd1JEVn8qtOoh3ohoYevWM5PVMDxwS0vKUQbuX0dvTvSMGRTP3yVi52JCxDGygD9jjtrO80Erlx7lZBHTZBze2rym66oNxYG6xeZCLZBHDk16zZBdhK5KhpziSOZBNfjZAB7MmzwnfbPv2ZBKBKJpUnEdQGNsqO3h96m17hBlHdtHTPSnXeiHBWUP8SO6YXPqjb7zZBfXS5IWbv0ZD";

    private UserService userService;

    public FBLoginController(UserService userService) {
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
    public ViewModel process(HttpServletRequest req, HttpServletResponse res) {
        ViewModel viewModel = new ViewModel("FB");
//        if (req.getMethod().equals(GET.toString())) {
//            return viewModel;
//        }


        FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN);
        //String token = generateAccessToken();
        //FacebookClient facebookClient = new DefaultFacebookClient(token);
        JsonObject userData = facebookClient.fetchObject("me", JsonObject.class, Parameter.with("fields", "name, first_name, email"));
        String nameJSON = userData.getString("name");
        String emailJSON = userData.getString("email");
        userService.create(new User(nameJSON, emailJSON, "0"));


        return viewModel;
    }

    private String generateAccessToken() {
        FacebookClient.AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken(MY_APP_ID, MY_APP_SECRET);
        String token = accessToken.getAccessToken();
        return token;
    }
}
