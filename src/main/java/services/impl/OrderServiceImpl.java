package services.interfaces;

import models.Order;

import java.util.List;

public interface OrderService {

    Order getById(int id) {

    };

    void delete(int id) {

    };

    void saveOrUpdate(Order order) {

    };

    List<Order> getAll() {

    };

}
