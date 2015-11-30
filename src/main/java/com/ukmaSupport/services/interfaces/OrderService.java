package com.ukmaSupport.services.interfaces;

import com.ukmaSupport.models.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {

    Order getById(int id);

    Order getByTime(java.sql.Date time);

    List<Order> getByAuditoriumNumber(String number);

    List<Order> getByWorkplaceAcessNum(int access_num);

    List<Order> getByUserId(int user_id);

    int getCountOrderByAssistantDate(String date_from,String date_to,int assistant_id,String status);

    int getCountOrderByDate(String date_from,String date_to,String status);

    void createOrUpdate(Order order);

    List<Order> getAllByAssisstIdDate(String date_from,String date_to,int id);

    List<Order> getAllByDate(String date_from, String date_to);

    List<Order> getAllByAuditoriumAndDate(String date_from,String date_to,String number);

    void delete(int id);

    void update(Order order);

    void updateAddingAssistantToAuditorium(int assistId, int audId);

    Order getByUserIdAndId(int user_id,int id);

    List<Order> getAll();

    List<Order> getAllAssistOrders(int assistid);
}
