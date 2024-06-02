package com.PMS.PMS.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long oId;
    private long orderNumber;
    private String orderDate;
    private int quantity;
    private int orderPrice;
    private String orderState;

//    @ManyToOne
//    @JoinColumn (name = "fk_product")
//    private Product product;

    private int productId;

    @ManyToOne
    @JoinColumn (name = "fk_customer")
    private Customer customer;




}
