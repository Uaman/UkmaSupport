package com.ukmaSupport.services.interfaces;

public interface RoleService {

    String getById(int id);

    void save(String role);

    void delete(int id);
}
