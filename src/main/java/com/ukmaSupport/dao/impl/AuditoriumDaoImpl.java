package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.AuditoriumDao;
import com.ukmaSupport.dao.mapper.AuditoriumMapper;
import com.ukmaSupport.models.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("auditoriumDao")
public class AuditoriumDaoImpl implements AuditoriumDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Auditorium auditorium) {
        this.jdbcTemplate.update("INSERT INTO auditorium(user_id,number) VALUES(?,?)",
                auditorium.getUserId(), auditorium.getNumber());
    }

    @Override
    public Auditorium getById(int id) {
        String sql = "SELECT id,user_id,number FROM auditorium WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new AuditoriumMapper());
    }

    @Override
    public void update(Auditorium auditorium) {
        this.jdbcTemplate.update("UPDATE auditorium SET user_id=?,number=? WHERE id=?",
                auditorium.getUserId(), auditorium.getNumber(), auditorium.getId());
    }

    @Override
    public Auditorium getByNumber(String number) {
        String sql = "SELECT id,user_id,number, concat(users.first_name, ' ', users.last_name) AS assist " +
                "FROM auditorium LEFT JOIN users ON users.id_user=auditorium.user_id " +
                "WHERE number=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{number}, new AuditoriumMapper());
    }

    @Override
    public void delete(int id) {
        this.jdbcTemplate.update("DELETE FROM auditorium WHERE id=?", id);
    }

    @Override
    public List<Auditorium> getAll() {
        String sql = "SELECT id,user_id,number, concat(users.first_name, ' ', users.last_name) AS assist " +
                "FROM auditorium LEFT JOIN users ON users.id_user=auditorium.user_id";
        return this.jdbcTemplate.query(sql, new AuditoriumMapper());
    }
}
