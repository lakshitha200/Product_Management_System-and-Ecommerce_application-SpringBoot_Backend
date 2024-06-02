package com.PMS.PMS.Service.Impl;

import com.PMS.PMS.Exception.ResourceNotFoundException;
import com.PMS.PMS.Model.Product;
import com.PMS.PMS.Repository.ProductRepository;
import com.PMS.PMS.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    //save product
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    //get all products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //get by id
    @Override
    public Product getProductById(long id) {
        Optional<Product> exsitingProduct = productRepository.findById(id);
        if(exsitingProduct.isPresent()){
            return exsitingProduct.get();
        }else {
            throw new ResourceNotFoundException("Cannot Find");
        }
    }

    //Update Products
    @Override
    public Product updateProduct(Product product, long id) {
        Product exsitingProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        exsitingProduct.setNumber(product.getNumber());
        exsitingProduct.setPname(product.getPname());
        exsitingProduct.setPrice(product.getPrice());
        exsitingProduct.setCategory(product.getCategory());
        exsitingProduct.setDescription(product.getDescription());
        exsitingProduct.setStock(product.getStock());
        exsitingProduct.setImg(product.getImg());
        exsitingProduct.setDiscountAvailable(product.isDiscountAvailable());
        exsitingProduct.setDiscount(product.getDiscount());

        productRepository.save(exsitingProduct);
        return exsitingProduct;
    }

    //Delete Product
    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        productRepository.deleteById(id);
    }


}
