package com.ukmaSupport.dao.impl;

import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.dao.mapper.AuditoriumMapper;
import com.ukmaSupport.dao.interfaces.AuditoriumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false,rollbackFor = Exception.class)
    @Override
    public void save(Auditorium auditorium) {
        this.template.update("INSERT INTO auditorium(user_id,number) VALUES(?,?)",
                auditorium.getUserId(), auditorium.getNumber());
    }

    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    @Override
    public Auditorium getById(int id) {
        String sql = "SELECT id,user_id,number FROM auditorium WHERE id=?";
        return (com.ukmaSupport.models.Auditorium) this.template.queryForObject(sql, new Object[]{id}, new AuditoriumMapper());

    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false,rollbackFor = Exception.class)
    @Override
    public void update(Auditorium auditorium) {
        this.template.update("UPDATE auditorium SET user_id=?,number=? WHERE id=?",
                auditorium.getUserId(), auditorium.getNumber(), auditorium.getId());
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    @Override
    public void deleteById(int id) {
        this.template.update("DELETE FROM auditorium WHERE id=?", id);
    }

    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    @Override
    public List<Auditorium> getAll() {
        String sql = "SELECT id,user_id,number FROM auditorium";
        return this.template.query(sql, new AuditoriumMapper());
    }
}
