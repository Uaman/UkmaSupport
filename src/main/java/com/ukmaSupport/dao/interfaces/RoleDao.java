package com.ukmaSupport.dao.interfaces;


public interface RoleDao {

    public String getRoleById(int id);

    public void addNewRole(String role);

    public void deleteRole(int id);

}
