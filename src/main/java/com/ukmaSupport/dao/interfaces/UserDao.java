package com.ukmaSupport.dao.interfaces;

import com.ukmaSupport.models.User;

import java.util.List;

public interface UserDao {

    User getByID(int id);

    void delete(int id);

    void saveOrUpdate(User user);

    List<User> getAll();

    User getByEmail(String email);
}