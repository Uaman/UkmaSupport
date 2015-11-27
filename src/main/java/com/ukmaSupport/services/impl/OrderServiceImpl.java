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
        Order order;
        try {
            order = orderDao.getById(id);
        } catch (Exception e) {
            order = null;
        }
        return order;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Order> getByAuditoriumNumber(String number) {
        return orderDao.getByAuditoriumNumber(number);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Order> getByWorkplaceAcessNum(int access_num) {
        return orderDao.getByWorkplaceAcessNum(access_num);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Order> getByUserId(int user_id) {
        return orderDao.getByUserId(user_id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public int getCountOrderByAssistant(int assistant_id, String status) {
        return orderDao.getCountOrderByAssistant(assistant_id, status);
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

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Order getByUserIdAndId(int user_id, int id) {
        Order order;
        try {
            order = orderDao.getByUserIdAndId(user_id, id);
        } catch (Exception e) {
            order = null;
        }
        return order;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Order> getAllAssistOrders(int assistid) {
        return orderDao.getAllAssistOrders(assistid);
    }

}
