package com.example.online_shop.dto;
import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import jakarta.validation.constraints.NotNull;

public class WishlistDto {
    @NotNull
    private Product product;

    @NotNull
    private User user;

    public WishlistDto(Product product, User user) {
        this.product = product;
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
