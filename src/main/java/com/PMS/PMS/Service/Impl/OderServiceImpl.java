package com.PMS.PMS.Service.Impl;

import com.PMS.PMS.Exception.ResourceNotFoundException;

import com.PMS.PMS.Model.Order;

import com.PMS.PMS.Repository.OrderRepository;
import com.PMS.PMS.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrdersById(long id) {
        Order exsitingOrder = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        return exsitingOrder;
    }

    @Override
    public Order updateOrder(Order order, long id) {
        Order order1 = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );

        order1.setOrderState(order.getOrderState());

        return orderRepository.save(order1);
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order is Not Found")
        );
        orderRepository.deleteById(id);
    }


}
