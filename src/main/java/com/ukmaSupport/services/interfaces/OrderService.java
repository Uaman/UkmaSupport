package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.Order;

import java.util.List;

public interface OrderService {

    Order getById(int id);

    List<Order> getByStatus(String status);

    List<Order> getByUserId(int user_id);

    void createOrUpdate(Order order);

    void delete(int id);

    List<Order> getAll();
}
