package com.ukmaSupport.dao.interfaces;

import com.ukmaSupport.models.User;

import java.util.List;

public interface UserDao {

    User getUserByID(int id);

    void deleteUser(int id);

    void updateUser(User user);

    List<User> getAll();

    User getUserByEmail(String email);
}