package com.PMS.PMS.Service.Impl;

import com.PMS.PMS.Dto.ProductDto;
import com.PMS.PMS.Exception.ResourceNotFoundException;
import com.PMS.PMS.Model.Products.Product;
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
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setNumber(productDto.getNumber());
        product.setModel(productDto.getModel());
        product.setBrand(productDto.getBrand());
        product.setName(productDto.getBrand()+" "+productDto.getModel());
        product.setType(productDto.getType());
        product.setPrice(productDto.getPrice());
        product.setDiscount(productDto.getDiscount());
        product.setDiscountAvailable(productDto.isDiscountAvailable());
        product.setNewPrice(productDto.getPrice() - (productDto.getPrice()*productDto.getDiscount()/100));
        product.setStock(productDto.getStock());
        product.setImages(productDto.getImages());
        product.setSpecifications(productDto.getSpecifications());
        product.setColors(productDto.getColors());

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
        Product exsitingProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        return exsitingProduct;
    }

    //Update Products
    @Override
    public Product updateProduct(ProductDto productDto, long id) {
        Product exsitingProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );

        exsitingProduct.setNumber(productDto.getNumber());
        exsitingProduct.setModel(productDto.getModel());
        exsitingProduct.setBrand(productDto.getBrand());
        exsitingProduct.setName(productDto.getBrand()+" "+productDto.getModel());
        exsitingProduct.setType(productDto.getType());
        exsitingProduct.setPrice(productDto.getPrice());
        exsitingProduct.setDiscount(productDto.getDiscount());
        exsitingProduct.setDiscountAvailable(productDto.isDiscountAvailable());
        exsitingProduct.setNewPrice(productDto.getPrice() - (productDto.getPrice()*productDto.getDiscount()/100));
        exsitingProduct.setStock(productDto.getStock());
        exsitingProduct.setImages(productDto.getImages());
        exsitingProduct.setSpecifications(productDto.getSpecifications());
        exsitingProduct.setColors(productDto.getColors());

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
