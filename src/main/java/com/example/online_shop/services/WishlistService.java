package com.example.online_shop.services;

import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import com.example.online_shop.models.Wishlist;
import com.example.online_shop.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<Wishlist> getWishlist(int id){
        return wishlistRepository.findByUser(userService.findById(id));
    }

    @Transactional
    public boolean addToWishlist(String name, int userId) {
        User user = userService.findById(userId);
        Product product = productService.findByName(name);

        if (wishlistRepository.existsByProductAndUser(product, user)) {
            return false;
        }else {
            Wishlist wishlist = new Wishlist();
            wishlist.setProduct(productService.findByName(name));
            wishlist.setUser(userService.findById(userId));
            wishlistRepository.save(wishlist);
            return true;
        }
    }

    public Wishlist findByNameAndUserId(String name, int userID){
        User user = userService.findById(userID);
        Product product = productService.findByName(name);
        return wishlistRepository.findByProductAndUser(product, user);
    }
    public void deleteByNameAndUserId(String name, int userID){
        wishlistRepository.delete(findByNameAndUserId(name, userID));
    }
}
