package com.PMS.PMS.Service.Impl;

import com.PMS.PMS.Exception.ResourceNotFoundException;
import com.PMS.PMS.Model.Wishlist;
import com.PMS.PMS.Repository.WishlistRepository;
import com.PMS.PMS.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public List<Wishlist> getAllWishlist() {
        return wishlistRepository.findAll();
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByID(long id) {
        Optional<Wishlist> exsitingWishlist = wishlistRepository.findById(id);
        if(exsitingWishlist.isPresent()){

            return exsitingWishlist.get();
        }else {
            throw new ResourceNotFoundException("Cannot Find");
        }
    }

    @Override
    public Wishlist updateWishlist(Wishlist wishlist, long id) {
        return null;
    }

    @Override
    public void deleteWishlist(long id) {
        wishlistRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        wishlistRepository.deleteById(id);
    }
}

