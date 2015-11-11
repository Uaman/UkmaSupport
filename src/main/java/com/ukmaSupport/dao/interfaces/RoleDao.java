package com.ukmaSupport.dao.interfaces;


public interface RoleDao {

    String getById(int id);

    void save(String role);

    void delete(int id);
}
