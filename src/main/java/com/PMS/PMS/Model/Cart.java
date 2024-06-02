package com.PMS.PMS.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String createAt;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(nullable = false)
    private int productId;

//    @ManyToOne
//    @JoinColumn(name = "product")
//    private Product product;

}
