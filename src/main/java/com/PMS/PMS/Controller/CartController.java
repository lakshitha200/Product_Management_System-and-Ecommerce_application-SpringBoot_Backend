package com.PMS.PMS.Controller;


import com.PMS.PMS.Dto.AdminRoleDto;
import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Model.Cart;
import com.PMS.PMS.Service.AdminService;
import com.PMS.PMS.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Get all cart
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER') or hasRole('CUSTOMER')")
    public List<Cart> getAllCart(){
        return cartService.getAllCarts();
    }

    // Create an Cart
    @PostMapping()
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart){
        return new ResponseEntity<Cart>(cartService.createCart(cart), HttpStatus.CREATED);
    }


    // Get Cart By Id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER') or hasRole('CUSTOMER')")
    public ResponseEntity<Cart> getCartByID(@PathVariable("id") long id){
        return new ResponseEntity<Cart>(cartService.getCartByID(id),HttpStatus.OK);
    }


    // Update Cart By Id
    @PutMapping("/{id}")
    @PreAuthorize(" hasRole('CUSTOMER')")
    public ResponseEntity<Cart> updateCart(@PathVariable("id") long id ,@RequestBody Cart cart){
        return new ResponseEntity<Cart>(cartService.updateCart(cart,id),HttpStatus.OK);
    }

    // Delete Cart By Id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> deleteWishlist(@PathVariable("id") long id){
        cartService.deleteCart(id);
        return new ResponseEntity<String>("Deleted",HttpStatus.OK);

    }
}
