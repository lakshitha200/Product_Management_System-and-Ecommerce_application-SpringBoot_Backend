package com.PMS.PMS.Controller;

import com.PMS.PMS.Model.Cart;
import com.PMS.PMS.Model.Wishlist;
import com.PMS.PMS.Service.CartService;
import com.PMS.PMS.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Get all Wishlist
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER') or hasRole('CUSTOMER')")
    public List<Wishlist> getAllWishlist(){
        return wishlistService.getAllWishlist();
    }

    // Create an Wishlist
    @PostMapping()
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist wishlist){
        return new ResponseEntity<Wishlist>(wishlistService.createWishlist(wishlist), HttpStatus.CREATED);
    }


    // Get Wishlist By Id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER') or hasRole('CUSTOMER')")
    public ResponseEntity<Wishlist> getWishlistByID(@PathVariable("id") long id){
        return new ResponseEntity<Wishlist>(wishlistService.getWishlistByID(id),HttpStatus.OK);
    }


    // Update Wishlist By Id
    @PutMapping("/{id}")
    @PreAuthorize(" hasRole('CUSTOMER')")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable("id") long id ,@RequestBody Wishlist wishlist){
        return new ResponseEntity<Wishlist>(wishlistService.updateWishlist(wishlist,id),HttpStatus.OK);
    }

    // Delete Wishlist By Id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> deleteWishlist(@PathVariable("id") long id){
        wishlistService.deleteWishlist(id);
        return new ResponseEntity<String>("Deleted",HttpStatus.OK);

    }
}