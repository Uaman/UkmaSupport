package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.Order;

import java.util.List;

public interface OrderService {

    Order getById(int id);

    void createOrUpdate(Order order);

    void delete(int id);

    List<Order> getAll();
}
