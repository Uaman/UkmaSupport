package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.Order;

import java.util.List;

public interface OrderService {

    Order getById(int id);

    List<Order> getByAuditoriumNumber(String number);

    List<Order> getByWorkplaceAcessNum(int access_num);

    List<Order> getByUserId(int user_id);

    int getCountOrderByAssistant(int assistant_id,String status);

    void createOrUpdate(Order order);

    List<Order> getAllByAssisstIdDate(String date_from,String date_to,int id);

    List<Order> getAllByDate(String date_from, String date_to);

    List<Order> getAllByAuditoriumAndDate(String date_from,String date_to,String number);

    void delete(int id);

    void update(Order order);

    Order getByUserIdAndId(int user_id,int id);

    List<Order> getAll();

    List<Order> getAllAssistOrders(int assistid);
}
