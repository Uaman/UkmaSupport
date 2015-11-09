package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.UserDao;
import com.ukmaSupport.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_USERS = "SELECT users.id_user, user_roles.role, users.first_name, users.last_name, users.email, users.data_entry, users.password, users.status_account FROM users INNER JOIN user_roles ON user_roles.id=users.user_roleid";

    private static final String GET_USER_BY_ID = "SELECT users.id_user, user_roles.role, users.first_name, users.last_name, users.email, users.data_entry, users.password, users.status_account FROM users INNER JOIN user_roles ON user_roles.id=users.user_roleid WHERE id_user = ? ";

    private static final String GET_USER_BY_EMAIL = "SELECT users.id_user, user_roles.role, users.first_name, users.last_name, users.email, users.data_entry, users.password, users.status_account FROM users INNER JOIN user_roles ON user_roles.id=users.user_roleid WHERE email = ? ";

    private static final String DELETE_USER = "DELETE FROM users WHERE id_user = ?";

    private static final String INSERT_QUERY = "INSERT INTO users (user_roleid, first_name, last_name, email, data_entry, password, status_account) VALUES((SELECT user_roles.id FROM user_roles WHERE user_roles.role=?),?,?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE users SET user_roleid=(SELECT user_roles.id FROM user_roles WHERE user_roles.role=?), first_name=?, last_name=?, email=?, data_entry=?, password=?, status_account=? WHERE id_user=?";

    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    @Override
    public User getById(int id) {
        List<User> users = jdbcTemplate.query(GET_USER_BY_ID, new Object[]{id}, rowMapper);
        return users.isEmpty() ? null : users.get(0);
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false,rollbackFor = Exception.class)
    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_USER, id);
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false,rollbackFor = Exception.class)
    @Override
    public void saveOrUpdate(final User user) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement prepStat;
                if (user.getId() == 0) {
                    prepStat = con.prepareStatement(INSERT_QUERY);
                    prepStat.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
                } else {
                    prepStat = con.prepareStatement(UPDATE_QUERY);
                    prepStat.setInt(8, user.getId());
                    prepStat.setDate(5, new java.sql.Date(user.getDateOfEntry().getTime()));
                }
                if (user.getRole() == null)
                    prepStat.setString(1, "user");
                else
                    prepStat.setString(1, user.getRole());
                if (user.getAccountStatus() == null)
                    prepStat.setString(7, "active");
                else
                    prepStat.setString(7, user.getAccountStatus());
                prepStat.setString(2, user.getFirstName());
                prepStat.setString(3, user.getLastName());
                prepStat.setString(4, user.getEmail());
                prepStat.setString(6, user.getPassword());
                return prepStat;
            }
        });
    }

    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, rowMapper);
    }

    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    @Override
    public User getByEmail(String email) {
        List<User> users = jdbcTemplate.query(GET_USER_BY_EMAIL, new Object[]{email}, rowMapper);
        return users.isEmpty() ? null : users.get(0);
    }

    private static final RowMapper<User> rowMapper = new RowMapper<User>() {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id_user"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setAccountStatus(rs.getString("status_account"));
            user.setDateOfEntry(new java.util.Date(rs.getDate("data_entry").getTime()));
            return user;
        }
    };
}