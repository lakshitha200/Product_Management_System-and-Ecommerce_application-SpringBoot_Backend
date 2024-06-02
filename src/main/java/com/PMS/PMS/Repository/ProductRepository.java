package com.PMS.PMS.Repository;

import com.PMS.PMS.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
