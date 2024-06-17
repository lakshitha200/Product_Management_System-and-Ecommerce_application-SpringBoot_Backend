package com.PMS.PMS.Controller;

import com.PMS.PMS.Model.Order;
import com.PMS.PMS.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER') or hasRole('CUSTOMER')")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping()
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        return new ResponseEntity<Order>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER') or hasRole('CUSTOMER')")
    public Order getOrdersById(@PathVariable("id") long id){
        return orderService.getOrdersById(id);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER') or hasRole('CUSTOMER')")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") long id ,@RequestBody Order order){
        return new ResponseEntity<Order>(orderService.updateOrder(order,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER') or hasRole('CUSTOMER')")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<String>("Deleted",HttpStatus.OK);

    }
}
