package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.User;

import java.util.List;

public interface UserService {

    User getById(int id);

    void delete(int id);

    void saveOrUpdate(User user);

    List<User> getAll();

    User getByEmail(String email);
}
