package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Type;
import java.sql.*;


public class RoleImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String GET_ROLE_BY_ID = "SELECT role FROM user_roles WHERE id = ?";

    private String ADD_NEW_ROLE = "INSERT INTO user_roles (role) VALUE (?)";

    private String DELETE_ROLE = "DELETE FROM user_roles WHERE id = ?";

    @Override
    public String getRoleById(int id) {
        return jdbcTemplate.queryForObject(GET_ROLE_BY_ID, new Object[]{id}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role");
            }
        });
    }

    @Override
    public void addNewRole(String role) {
        jdbcTemplate.update(ADD_NEW_ROLE,new Object[]{role},new Object[]{Types.VARCHAR});
    }

    @Override
    public void deleteRole(int id) {
        jdbcTemplate.update(DELETE_ROLE,new Object[]{id});
    }
}
