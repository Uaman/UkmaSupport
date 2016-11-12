package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.Auditorium;

import java.util.List;

public interface AuditoriumService {
    //Create
    void save(Auditorium auditorium);

    //Read
    Auditorium getById(int id);

    //Update
    void update(Auditorium auditorium);

    Auditorium getByNumber(String number);
    //Delete
    void delete(int id);

    //Get All
    List<Auditorium> getAll();
}
