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

    List<Order> getAll();

    List<Order> getByUserAndStatus(int userid, String status);

    List<Order> getByAssistAndStatus(int assistid, String status);

    List<Order> getByAssist(int assistid);
}
