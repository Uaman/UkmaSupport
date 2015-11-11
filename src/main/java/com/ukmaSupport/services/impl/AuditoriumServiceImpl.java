package com.ukmaSupport.services.impl;

import com.ukmaSupport.dao.interfaces.AuditoriumDao;
import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("auditoriumService")
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumDao auditoriumDao;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void save(Auditorium auditorium) {
        auditoriumDao.save(auditorium);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Auditorium getById(int id) {
        return auditoriumDao.getById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void update(Auditorium auditorium) {
        auditoriumDao.update(auditorium);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void delete(int id) {
        auditoriumDao.delete(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Auditorium> getAll() {
        return auditoriumDao.getAll();
    }
}
