package com.ukmaSupport.services.impl;

import com.ukmaSupport.dao.interfaces.AuditoriumDao;
import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("auditoriumService")
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumDao auditoriumDao;

    @Autowired
    private OrderService orderService;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void save(Auditorium auditorium) {
        auditoriumDao.save(auditorium);
    }



    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Auditorium getById(int id) {
        Auditorium auditorium;
        try {
            auditorium = auditoriumDao.getById(id);
        } catch (Exception e) {
            auditorium = null;
        }
        return auditorium;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void update(Auditorium auditorium) {
        auditoriumDao.update(auditorium);
        orderService.updateAddingAssistantToAuditorium(auditorium.getUserId(), auditorium.getId());
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Auditorium getByNumber(String number) {
        Auditorium auditorium;
        try {
            auditorium = auditoriumDao.getByNumber(number);
        } catch (Exception e) {
            auditorium = null;
        }
        return auditorium;
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
