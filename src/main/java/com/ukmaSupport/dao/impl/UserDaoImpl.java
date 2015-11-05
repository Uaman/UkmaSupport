package com.ukmaSupport.dao.impl;

import com.ukmaSupport.models.User;
import com.ukmaSupport.dao.interfaces.UserDao;
import com.ukmaSupport.utils.JavaDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String GET_USER_BY_ID = "SELECT * FROM user WHERE id_user = ? ";

    private String DELETE_USER = "DELETE FROM user WHERE id_user = ?";

    private String CREATE_USER = "";

    @Override
    public User getUserByID(final int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID,
                new ParameterizedRowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setFirstName(rs.getString("first_name"));
                        user.setLastName(rs.getString("last_name"));
                        user.setEmail(rs.getString("email"));
                        user.setAccountStatus(rs.getString("status_account"));
                        //user.setRole();
                        user.setDateOfEntry(JavaDateConverter.convertToJavaDate(rs.getDate("dateOfEntry")));
                        return user;
                    }
                }, id);
    }

    @Override
    public void createUser(String firstname, String lastname, int userRoleID, String email, Date dateOfEntry, String statusAccount, String password) {

    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(DELETE_USER, id);
    }

    @Override
    public void updateUser(int id, String firstname, String lastname, int userRoleID, String email, Date dateOfEntry, String statusAccount, String password) {
    }

    @Override
    public User getUserByName(String firstname, String lastname) {
        return null;
    }
}
