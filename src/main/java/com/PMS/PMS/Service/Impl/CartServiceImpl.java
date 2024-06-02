package com.PMS.PMS.Service.Impl;


import com.PMS.PMS.Exception.ResourceNotFoundException;
import com.PMS.PMS.Model.Cart;
import com.PMS.PMS.Repository.CartRepository;
import com.PMS.PMS.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByID(long id) {
        Optional<Cart> exsitingCart = cartRepository.findById(id);
        if(exsitingCart.isPresent()){

            return exsitingCart.get();
        }else {
            throw new ResourceNotFoundException("Cannot Find");
        }
    }

    @Override
    public Cart updateCart(Cart cart, long id) {
        return null;
    }

    @Override
    public void deleteCart(long id) {
        cartRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        cartRepository.deleteById(id);
    }
}
