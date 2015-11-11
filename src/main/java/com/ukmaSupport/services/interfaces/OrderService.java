package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.Order;

import java.util.List;

public interface OrderService {

    Order getById(int id);

    List<Order> getByStatus(String status);

    void createOrUpdate(Order order);

    void delete(int id);

    List<Order> getAll();
}
