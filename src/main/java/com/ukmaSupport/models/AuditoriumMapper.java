package com.ukmaSupport.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by viktor on 04.11.15.
 */
// Map each row of the result set object to the Auditorium POJO object
public class AuditoriumMapper implements RowMapper<Auditorium> {
    @Override
    public Auditorium mapRow(ResultSet resultSet, int i) throws SQLException {
        Auditorium auditorium = new Auditorium();
        auditorium.setId(resultSet.getInt("id"));
        auditorium.setUserId(resultSet.getInt("userid"));
        auditorium.setNumber(resultSet.getString("number"));
        return auditorium;
    }
}
