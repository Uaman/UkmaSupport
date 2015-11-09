package com.ukmaSupport.dao.impl;

import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.dao.mapper.AuditoriumMapper;
import com.ukmaSupport.dao.interfaces.AuditoriumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("auditoriumDao")
public class AuditoriumDaoImpl implements AuditoriumDao {
    @Autowired
    private JdbcTemplate template;

    public AuditoriumDaoImpl() {
    }

    public AuditoriumDaoImpl(JdbcTemplate template) throws SQLException {
        this.template = template;
    }

    @Override
    public void save(Auditorium auditorium) {
        this.template.update("INSERT INTO auditorium(user_id,number) VALUES(?,?)",
                auditorium.getUserId(), auditorium.getNumber());
    }

    @Override
    public Auditorium getById(int id) {
        String sql = "SELECT id,user_id,number FROM auditorium WHERE id=?";
        return (com.ukmaSupport.models.Auditorium) this.template.queryForObject(sql, new Object[]{id}, new AuditoriumMapper());

    }

    @Override
    public void update(Auditorium auditorium) {
        this.template.update("UPDATE auditorium SET user_id=?,number=? WHERE id=?",
                auditorium.getUserId(), auditorium.getNumber(), auditorium.getId());
    }

    @Override
    public void delete(int id) {
        this.template.update("DELETE FROM auditorium WHERE id=?", id);
    }

    @Override
    public List<Auditorium> getAll() {
        String sql = "SELECT id,user_id,number FROM auditorium";
        return this.template.query(sql, new AuditoriumMapper());
    }
}
