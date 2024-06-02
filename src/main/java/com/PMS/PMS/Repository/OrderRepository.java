package com.PMS.PMS.Repository;


import com.PMS.PMS.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
