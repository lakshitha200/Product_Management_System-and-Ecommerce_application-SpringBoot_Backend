package com.PMS.PMS.Service;

import com.PMS.PMS.Model.Wishlist;

import java.util.List;

public interface WishlistService {
    List<Wishlist> getAllWishlist();

    Wishlist createWishlist(Wishlist wishlist);

    Wishlist getWishlistByID(long id);

    Wishlist updateWishlist(Wishlist wishlist, long id);

    void deleteWishlist(long id);
}
