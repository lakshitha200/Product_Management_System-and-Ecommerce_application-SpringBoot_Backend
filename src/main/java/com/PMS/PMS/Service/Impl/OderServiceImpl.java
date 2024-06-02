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
        Optional<Order> exsitingOrder = orderRepository.findById(id);
        if (exsitingOrder.isPresent()) {
            return exsitingOrder.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find");
        }
    }

    @Override
    public Order updateOrder(Order order, long id) {
        return null;
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order is Not Found")
        );
        orderRepository.deleteById(id);
    }


}
