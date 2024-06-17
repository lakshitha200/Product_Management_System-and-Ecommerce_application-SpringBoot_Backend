package com.PMS.PMS.Dto;

import com.PMS.PMS.Model.Products.PColors;
import com.PMS.PMS.Model.Products.ProductImages;
import com.PMS.PMS.Model.Products.ProductSpecifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private int number;
    private String model;
    private String brand;
    private String type;
    private double price;
    private double discount;
    private boolean discountAvailable;
    private int stock;
    private List<ProductImages> images;
    private ProductSpecifications specifications;
    private List<PColors> colors;
}
