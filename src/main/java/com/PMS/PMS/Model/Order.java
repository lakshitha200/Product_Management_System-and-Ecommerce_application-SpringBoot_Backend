package com.PMS.PMS.Model;

import com.PMS.PMS.Model.Products.Product;
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
    private String orderState;
    private String colour;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "fk_product")
    private Product product;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "fk_customer")
    private Customer customer;




}
