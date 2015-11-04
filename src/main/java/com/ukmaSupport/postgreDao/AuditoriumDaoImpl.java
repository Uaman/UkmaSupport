package com.ukmaSupport.postgreDao;

import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.models.AuditoriumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by viktor on 04.11.15.
 */
@Repository("auditoriumDao")
public class AuditoriumDaoImpl implements AuditoriumDao {
    @Autowired
    private JdbcTemplate template;

    public AuditoriumDaoImpl(){
    }

    public AuditoriumDaoImpl(JdbcTemplate template) throws SQLException {
        this.template = template;
    }
    @Override
    public void save(Auditorium auditorium) {
        this.template.update("INSERT INTO auditorium(userid,number) VALUES(?,?)",
                auditorium.getUserId(), auditorium.getNumber());
    }

    @Override
    public Auditorium getById(int id) {
        String sql = "SELECT id,userid,number FROM auditorium WHERE id=?";
        return (Auditorium) this.template.queryForObject(sql, new Object[]{id}, new AuditoriumMapper());

    }

    @Override
    public void update(Auditorium auditorium) {
        this.template.update("UPDATE auditorium SET userid=?,number=? WHERE id=?",
                auditorium.getUserId(), auditorium.getNumber(),auditorium.getId());
    }

    @Override
    public void deleteById(int id) {
        this.template.update("DELETE FROM auditorium WHERE id=?", id);
    }

    @Override
    public List<Auditorium> getAll() {
        String sql = "SELECT id,userid,number FROM auditorium";
        return this.template.query(sql, new AuditoriumMapper());
    }
}
