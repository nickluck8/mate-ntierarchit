package com.nick.dao;

import com.nick.model.User;

public interface UserDao extends GenericDao<User> {


    User getUserByToken(String token);

}
