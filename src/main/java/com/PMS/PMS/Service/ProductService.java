package com.PMS.PMS.Service;

import com.PMS.PMS.Model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(long id);
    Product updateProduct(Product product,long id);
    public void deleteProduct(long id);
}
