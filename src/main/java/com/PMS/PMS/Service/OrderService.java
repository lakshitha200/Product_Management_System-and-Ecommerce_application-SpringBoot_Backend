package com.PMS.PMS.Service;

import com.PMS.PMS.Model.Customer;
import com.PMS.PMS.Model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order createOrder(Order order);

    Order getOrdersById(long id);
    Order updateOrder(Order order, long id);

    void deleteOrder(long id);




}
