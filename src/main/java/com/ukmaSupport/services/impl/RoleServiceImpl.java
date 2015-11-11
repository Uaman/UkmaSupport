package com.ukmaSupport.services.impl;

import com.ukmaSupport.dao.interfaces.RoleDao;
import com.ukmaSupport.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public String getById(int id) {
        return roleDao.getById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void save(String role) {
        roleDao.save(role);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void delete(int id) {
        roleDao.delete(id);
    }
}
