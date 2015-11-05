package com.ukmaSupport.dao.mapper;

import com.ukmaSupport.models.Workplace;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkplaceMapper implements RowMapper<Workplace> {
    @Override
    public Workplace mapRow(ResultSet resultSet, int i) throws SQLException {
        Workplace workplace = new Workplace();
        workplace.setId(resultSet.getInt("id"));
        workplace.setAuditoriumId(resultSet.getInt("auditorium_id"));
        workplace.setAccessNumber(resultSet.getInt("access_num"));
        return workplace;
    }
}
