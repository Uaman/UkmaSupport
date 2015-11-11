package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.Workplace;

import java.util.List;

public interface WorkplaceService {

    //Create
    void save(Workplace workplace);

    //Read
    Workplace getById(int id);

    //Read
    Workplace getByNumber(int number);

    //Read
    List<Workplace> getByAuditoryName(String name);

    //Update
    void update(Workplace workplace);

    //Delete
    void delete(int id);

    //Get All
    List<Workplace> getAll();
}
