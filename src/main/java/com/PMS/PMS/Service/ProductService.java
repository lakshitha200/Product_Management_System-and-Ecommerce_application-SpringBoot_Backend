package com.PMS.PMS.Service;

import com.PMS.PMS.Dto.ProductDto;
import com.PMS.PMS.Model.Products.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    List<Product> getAllProducts();
    Product getProductById(long id);
    Product updateProduct(ProductDto productDto,long id);
    public void deleteProduct(long id);
}
