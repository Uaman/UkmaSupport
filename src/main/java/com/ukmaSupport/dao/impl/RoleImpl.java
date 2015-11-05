package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Dima on 05.11.2015.
 */
public class RoleImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String GET_ROLE_BY_ID = "SELE";

    @Override
    public String getRoleById(int id) {
        return null;//jdbcTemplate.queryForObject(GET_ROLE_BY_ID,id);
    }

    @Override
    public void addNewRole(String role) {

    }

    @Override
    public void updateRole(String role) {

    }

    @Override
    public void deleteRole(int id) {

    }
}
