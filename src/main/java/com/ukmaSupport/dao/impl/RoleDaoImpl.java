package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ROLE_BY_ID = "SELECT role FROM user_roles WHERE id = ?";

    private static final String ADD_NEW_ROLE = "INSERT INTO user_roles (role) VALUE (?)";

    private static final String DELETE_ROLE = "DELETE FROM user_roles WHERE id = ?";

    @Override
    public String getById(int id) {
        return jdbcTemplate.queryForObject(GET_ROLE_BY_ID, new Object[]{id}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role");
            }
        });
    }

    @Override
    public void save(String role) {
        jdbcTemplate.update(ADD_NEW_ROLE, new Object[]{role}, new Object[]{Types.VARCHAR});
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_ROLE, id);
    }
}
