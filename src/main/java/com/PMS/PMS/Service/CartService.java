package com.PMS.PMS.Service;

import com.PMS.PMS.Model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();

    Cart createCart(Cart cart);

    Cart getCartByID(long id);

    Cart updateCart(Cart cart, long id);

    void deleteCart(long id);
}
