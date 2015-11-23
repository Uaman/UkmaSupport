package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.Order;

import java.util.List;

public interface OrderService {

    Order getById(int id);

    List<Order> getByStatus(String status);

    List<Order> getByAuditoriumNumber(String number);

    List<Order> getByWorkplaceAcessNum(int access_num);

    List<Order> getByUserId(int user_id);

    List<Order> getByUserIdStatus(int user_id,String status);

    void createOrUpdate(Order order);

    void delete(int id);

    void update(Order order);

    Order getByUserIdAndId(int user_id,int id);

    List<Order> getAll();

    List<Order> getAllAssistOrders(int assistid);

    List<Order> getByAssistAndStatus(int assistid, String status);

    int getUserOrdersCount(int id);
}
