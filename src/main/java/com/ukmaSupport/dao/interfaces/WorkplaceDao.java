package com.ukmaSupport.dao.interfaces;

import com.ukmaSupport.models.Workplace;

import java.util.List;

public interface WorkplaceDao {
    //Create
    void save(Workplace workplace);

    //Read
    Workplace getById(int id);

    //Read
    List<Workplace> getByAuditorium(int id);

    //Read
    List<Workplace> getByAuditoriumName(String name);

    //Update
    void update(Workplace workplace);

    //Delete
    void delete(int id);

    //Get All
    List<Workplace> getAll();
}
