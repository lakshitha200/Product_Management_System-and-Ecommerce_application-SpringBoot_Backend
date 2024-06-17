package com.PMS.PMS.Controller;

import com.PMS.PMS.Dto.ProductDto;
import com.PMS.PMS.Model.Products.Product;
import com.PMS.PMS.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // create product
    @PostMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER')")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<Product>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    // Get All product
    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // Get product By Id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productId){
        return new ResponseEntity<Product>(productService.getProductById(productId),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER')")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id ,@RequestBody ProductDto productDto){
        return new ResponseEntity<Product>(productService.updateProduct(productDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Deleted",HttpStatus.OK);

    }
}
