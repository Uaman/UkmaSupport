package com.ukmaSupport.dao.interfaces;

import com.ukmaSupport.models.User;

import java.util.List;

public interface UserDao {

    User getById(int id);

    void delete(int id);

    void saveOrUpdate(User user);

    User getResponsibleAssistant(String auditorium);

    List<User> getAll();

    User getByEmail(String email);

    List<User> getByRole(String role);

    List<User> getByStatus(String status);
}