package com.ukmaSupport.postgreDao.impl;

import com.ukmaSupport.POJO.models.User;
import com.ukmaSupport.postgreDao.IUserDao;
import com.ukmaSupport.utils.JavaDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dima on 04.11.2015.
 */
public class UserDao implements IUserDao {

    @Autowired
    private SimpleJdbcTemplate jdbcTemplate;

    private String GET_USER_BY_ID = "SELECT * FROM user WHERE id_user = ? ";

    private String DELETE_USER = "DELETE FROM user WHERE id_user = ?";

    private String CREATE_USER = "";


    @Override
    public User getUserByID(final int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID,
                new ParameterizedRowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setFirstName(rs.getString("firstname"));
                        user.setLastName(rs.getString("lastname"));
                        user.setEmail(rs.getString("email"));
                        user.setAccountStatus(rs.getString("statusaccount"));
                        //user.setRole();
                        user.setDateOfEntry(JavaDateConverter.convertToJavaDate(rs.getDate("dateOfEntry")));
                        return user;
                    }
                },id);

    }

    @Override
    public void createUser(String firstname, String lastname, int userRoleID, String email, Date dateOfEntry, String statusAccount, String password) {

    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(DELETE_USER,id);
    }

    @Override
    public void updateUser(int id, String firstname, String lastname, int userRoleID, String email, Date dateOfEntry, String statusAccount, String password) {

    }

    @Override
    public User getUserByName(String firstname, String lastname) {
        return null;
    }
}
