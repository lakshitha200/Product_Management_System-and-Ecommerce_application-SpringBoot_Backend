package com.PMS.PMS.Model.Products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_colours")
public class PColors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
