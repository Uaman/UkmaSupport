package com.ukmaSupport.dao.interfaces;

/**
 * Created by Dima on 05.11.2015.
 */
public interface RoleDao {

    public String getRoleById(int id);

    public void addNewRole(String role);

    public void updateRole(String role);

    public void deleteRole(int id);

}
