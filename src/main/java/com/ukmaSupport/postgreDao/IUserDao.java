package com.ukmaSupport.postgreDao;

import com.ukmaSupport.POJO.User;

public interface IUserDao {

    public User getUserByID(int id);

    public void createUser(String firstname, String lastname,
                           int userRoleID, String email, java.sql.Date dateOfEntry,
                           String statusAccount, String password);

    public void deleteUser(int id);

    public void updateUser(int id, String firstname, String lastname,
                           int userRoleID, String email, java.sql.Date dateOfEntry,
                           String statusAccount, String password);

    public User getUserByName(String firstname, String lastname);
}