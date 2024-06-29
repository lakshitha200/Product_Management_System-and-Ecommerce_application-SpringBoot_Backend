package com.PMS.PMS.Model.Products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products_specifications")
public class ProductSpecifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String display;
    private String battery;
    private String ram;

    private String chipset;
    private String technology;
    private String storage;
    private String camera;

    private String processor;
    private String graphics;
    private String features;
    private String size;



}