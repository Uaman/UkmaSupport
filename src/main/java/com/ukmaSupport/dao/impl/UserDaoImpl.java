package com.ukmaSupport.dao.impl;

import com.ukmaSupport.models.User;
import com.ukmaSupport.dao.interfaces.UserDao;
import com.ukmaSupport.utils.JavaDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_USERS = "SELECT * FROM user";

    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id_user = ? ";

    private static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ? ";

    private static final String DELETE_USER = "DELETE FROM user WHERE id_user = ?";

    private static final String INSERT_QUERY = "INSERT INTO user (user_roleid, first_name, last_name, email, data_entry, password, status_account) VALUES((SELECT userrole.id FROM userrole WHERE user_role.role=?),?,?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE user SET user_roleid=(SELECT userrole.id FROM userrole WHERE user_role.role=?), first_name=?, last_name=?, email=?, data_entry=?, password=?, status_account WHERE id=?";

    @Override
    public User getUserByID(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID, new Object[]{id}, rowMapper);
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(DELETE_USER, id);
    }

    @Override
    public void updateUser(final User user) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement prepStat;
                if (user.getId() == 0) {
                    prepStat = con.prepareStatement(INSERT_QUERY);
                    prepStat.setDate(5, (java.sql.Date) java.util.Calendar.getInstance().getTime());
                } else {
                    prepStat = con.prepareStatement(UPDATE_QUERY);
                    prepStat.setDate(5, (java.sql.Date) user.getDateOfEntry());
                }
                prepStat.setString(1, user.getRole());
                prepStat.setString(2, user.getFirstName());
                prepStat.setString(3, user.getLastName());
                prepStat.setString(4, user.getEmail());
                prepStat.setString(6, user.getPassword());
                prepStat.setString(7, user.getAccountStatus());
                return prepStat;
            }
        });
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, rowMapper);
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.queryForObject(GET_USER_BY_EMAIL, new Object[]{email}, rowMapper);
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
            user.setRole(rs.getString("user_roleid"));//переробити норм
            user.setAccountStatus(rs.getString("status_account"));
            user.setDateOfEntry(JavaDateConverter.convertToJavaDate(rs.getDate("data_entry")));
            return user;
        }
    };
}