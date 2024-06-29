package com.PMS.PMS.Model.Products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int number;
    private String model;
    private String brand;
    private String name;
    private String type;
    private double price;
    private double discount;
    private boolean discountAvailable;
    private double newPrice;
    private int stock;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "images")
    private List<ProductImages> images;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specifications", referencedColumnName = "id")
    private ProductSpecifications specifications;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "colors")
    private List<PColors> colors;





}
