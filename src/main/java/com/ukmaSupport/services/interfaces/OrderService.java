package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.Order;

import java.util.List;

public interface OrderService {

    Order getById(int id);

    List<Order> getByStatus(String status);

    List<Order> getByUserId(int user_id);

    List<Order> getByUserIdStatus(int user_id,String status);

    void createOrUpdate(Order order);

    void delete(int id);

    void update(Order order);

    List<Order> getAll();

    List<Order> getAllAssistOrders(int assistid);

    List<Order> getByAssistAndStatus(int assistid, String status);
}
