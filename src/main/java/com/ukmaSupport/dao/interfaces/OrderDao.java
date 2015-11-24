package com.ukmaSupport.dao.interfaces;

import com.ukmaSupport.models.Order;

import java.util.List;

public interface OrderDao {

    Order getById(int id);

    List<Order> getByStatus(String status);

    List<Order> getByAuditoriumNumber(String number);

    List<Order> getByWorkplaceAcessNum(int access_num);

    List<Order> getByUserId(int user_id);

    List<Order> getUserStatus(int user_id,String status);

    void createOrUpdate(Order order);

    void update(Order order);

    Order getByUserIdAndId(int user_id,int id);

    void delete(int id);

    List<Order> getAll();

    List<Order> getAllAssistOrders(int assistid);

    List<Order> getByAssistAndStatus(int assistid, String status);

}