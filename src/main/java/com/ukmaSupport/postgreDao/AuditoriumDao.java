package com.ukmaSupport.postgreDao;

import com.ukmaSupport.POJO.Auditorium;

import java.util.List;

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
