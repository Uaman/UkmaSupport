package com.ukmaSupport.postgreDao;

import com.ukmaSupport.models.Auditorium;

import java.util.List;

/**
 * Created by viktor on 04.11.15.
 */
public interface AuditoriumDao {
    //Create
    public void save(Auditorium auditorium);
    //Read
    public Auditorium getById(int id);
    //Update
    public void update(Auditorium auditorium);
    //Delete
    public void deleteById(int id);
    //Get All
    public List<Auditorium> getAll();
}
