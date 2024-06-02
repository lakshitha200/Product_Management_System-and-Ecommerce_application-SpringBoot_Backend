package com.PMS.PMS.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private long number;
    @Column(nullable = false, unique = true)
    private String pname;
    private double price;
    private String category;
    private String description;
    private int stock;

//    @OneToMany (cascade = CascadeType.ALL)
//    @JoinColumn (name = "fk_productImages")
    private String img;

    private boolean discountAvailable;
    private double discount;

    public boolean isDiscountAvailable() {
        return discountAvailable;
    }



}
