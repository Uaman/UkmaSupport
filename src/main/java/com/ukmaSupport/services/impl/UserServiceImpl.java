package com.ukmaSupport.services.impl;

import com.ukmaSupport.dao.interfaces.UserDao;
import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveOrUpdate(User user) {
        if (user.getRole() == null)
            user.setRole("user");
        if (user.getAccountStatus() == null)
            user.setAccountStatus("active");
        userDao.saveOrUpdate(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}