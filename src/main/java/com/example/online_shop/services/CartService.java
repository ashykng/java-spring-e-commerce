package com.example.online_shop.services;

import com.example.online_shop.controllers.CartController;
import com.example.online_shop.models.Cart;
import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import com.example.online_shop.models.Wishlist;
import com.example.online_shop.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<Cart> getCart(int id){
        return cartRepository.findByUser(userService.findById(id));
    }

    @Transactional
    public boolean addToCart(String name, int userId){
        User user = userService.findById(userId);
        Product product = productService.findByName(name);

        if (cartRepository.existsByProductAndUser(product, user)) {
            return false;
        }else {
            Cart cart = new Cart();
            cart.setProduct(productService.findByName(name));
            cart.setUser(userService.findById(userId));
            cartRepository.save(cart);
            return true;
        }
    }

    public Cart findByNameAndUserId(String name, int userID){
        User user = userService.findById(userID);
        Product product = productService.findByName(name);
        return cartRepository.findByProductAndUser(product, user);
    }

    public void deleteByNameAndUserId(String name, int userID){
        cartRepository.delete(findByNameAndUserId(name, userID));
    }


    public Double getTotalPriceByUser(int userId) {
        List<Cart> cartItems = getCart(userId);
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }


    @Transactional
    public void deleteAllCartItemsByUser(int userId) {
        User user = userService.findById(userId);
        cartRepository.deleteAllByUser(user);
    }

    @Transactional
    public void updateQuantities(List<CartController.UpdateQuantityRequest> requests, User user) {
        for (CartController.UpdateQuantityRequest request : requests) {
            Product product = productService.findById(request.getProductId());
            Cart cart = cartRepository.findByProductAndUser(product, user);
            if (cart != null) {
                cart.setQuantity(request.getQuantity());
                cartRepository.save(cart);
            }
        }
    }

}
