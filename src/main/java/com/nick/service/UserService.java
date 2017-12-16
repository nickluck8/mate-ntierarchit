package com.nick.service;

import com.nick.model.User;

import java.util.Optional;

public interface UserService {
    User create(User user);

    User update(User user);

    User delete(User user);

    Optional<String> checkUser(String email, String password);
}
