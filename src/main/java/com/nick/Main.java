package com.nick;

import com.nick.controller.UserController;
import com.nick.model.Reflection;
import com.nick.model.User;

public class Main {

    public static void main(String[] args) {

        UserController userController = new UserController(Factory
                .getUserService(Factory
                        .getUserDao(Factory
                                .getConnection())));
        User user = new User("Joe", "email@email", "34");

        //user.setId(12L);
        //userController.create(user);
        //user.setName("ojasd");
        //user.setPassword("asdasd");
        //userController.update(user);
        //User user1 = new User("ser", "email2#", " asdasdasd");
        //userController.create(user);
        user.setId(17L);
        //user.setEmail("asdasd");
        user.setPassword("asdasd");
        userController.update(user);
        userController.create(user);
        //userController.delete(user);
        //user1.setPassword("Sadasdasd");
        //userController.update(user1);
        //UserDaoImpl userDao = new UserDaoImpl();
        //userDao.getAnnotations(user);

        //userController.create(user);
        //userDao.create(user);
        //System.out.println(s);
        System.out.println(Reflection.getMap(user) + " ID: " + user.getId());
        //System.out.println(Reflection.getMap(user1) + " ID: " + user1.getId());

    }

}