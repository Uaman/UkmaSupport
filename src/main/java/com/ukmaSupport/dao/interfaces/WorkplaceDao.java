package com.ukmaSupport.dao.interfaces;



import com.ukmaSupport.models.Workplace;

import java.util.List;

public interface WorkplaceDao {
    //Create
    public void save(Workplace workplace);

    //Read
    public Workplace getById(int id);

    //Update
    public void update(Workplace workplace);

    //Delete
    public void deleteById(int id);

    //Get All
    public List<Workplace> getAll();
}
