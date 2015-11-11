package com.ukmaSupport.services.impl;

import com.ukmaSupport.dao.interfaces.OrderDao;
import com.ukmaSupport.models.Order;
import com.ukmaSupport.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Order getById(int id) {
        return orderDao.getById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Order> getByStatus(String status) {
        return orderDao.getByStatus(status);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void createOrUpdate(Order order) {
        orderDao.createOrUpdate(order);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void delete(int id) {
        orderDao.delete(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
