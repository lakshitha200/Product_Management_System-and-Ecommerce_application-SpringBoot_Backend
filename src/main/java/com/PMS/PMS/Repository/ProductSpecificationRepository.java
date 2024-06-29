package com.PMS.PMS.Repository;

import com.PMS.PMS.Model.Products.ProductSpecifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecificationRepository extends JpaRepository<ProductSpecifications,Long> {

}
