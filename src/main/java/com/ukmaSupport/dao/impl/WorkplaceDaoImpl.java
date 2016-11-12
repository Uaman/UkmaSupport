package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.WorkplaceDao;
import com.ukmaSupport.dao.mapper.WorkplaceMapper;
import com.ukmaSupport.models.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("workplaceDao")
public class WorkplaceDaoImpl implements WorkplaceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Workplace workplace) {
        this.jdbcTemplate.update("INSERT INTO workplace (auditorium_id,access_num) VALUES(?,?)",
                workplace.getAuditoriumId(), workplace.getAccessNumber());
    }

    @Override
    public Workplace getById(int id) {
        String sql = "SELECT id,auditorium_id,acces_num FROM workplace WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new WorkplaceMapper());
    }

    @Override
    public Workplace getByNumber(int number) {
        String sql = "SELECT id,auditorium_id,access_num FROM  workplace WHERE access_num=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{number}, new WorkplaceMapper());
    }

    @Override
    public List<Workplace> getByAuditoriumName(String name) {
        String sql = "SELECT workplace.id, workplace.auditorium_id, workplace.access_num " +
                "FROM workplace INNER JOIN auditorium ON workplace.auditorium_id = auditorium.id " +
                "WHERE auditorium.number = ?";
        return this.jdbcTemplate.query(sql, new Object[]{name}, new WorkplaceMapper());
    }

    @Override
    public void update(Workplace workplace) {
        this.jdbcTemplate.update("UPDATE workplace SET auditorium_id=?,access_num=? WHERE id=?",
                workplace.getAuditoriumId(), workplace.getAccessNumber(), workplace.getId());
    }

    @Override
    public void delete(int id) {
        this.jdbcTemplate.update("DELETE FROM workplace WHERE id=?", id);
    }

    @Override
    public List<Workplace> getAll() {
        String sql = "SELECT id,auditorium_id,access_num FROM workplace";
        return this.jdbcTemplate.query(sql, new WorkplaceMapper());
    }
}
