package com.ukmaSupport.services.impl;

import com.ukmaSupport.dao.interfaces.WorkplaceDao;
import com.ukmaSupport.models.Workplace;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("workplaceService")
public class WorkplaceServiceImpl implements WorkplaceService {

    @Autowired
    private WorkplaceDao workplaceDao;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void save(Workplace workplace) {
        workplaceDao.save(workplace);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Workplace getById(int id) {
        Workplace workplace;
        try {
            workplace = workplaceDao.getById(id);
        } catch (Exception e) {
            workplace = null;
        }
        return workplace;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Workplace getByNumber(int number) {
        Workplace workplace;
        try {
            workplace = workplaceDao.getByNumber(number);
        } catch (Exception e) {
            workplace = null;
        }
        return workplace;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Workplace> getByAuditoryName(String name) {
        return workplaceDao.getByAuditoriumName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void update(Workplace workplace) {
        workplaceDao.update(workplace);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void delete(int id) {
        workplaceDao.delete(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Workplace> getAll() {
        return workplaceDao.getAll();
    }
}
