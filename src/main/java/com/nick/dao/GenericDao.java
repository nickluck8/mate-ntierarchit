package com.nick.dao;

import com.nick.model.User;

public interface GenericDao<T> {

    T create(T t);

    T findById(Long id);

    T update(User user);

    T delete(User user);

    User findByEmail(String email);

    User update(String token);
}
