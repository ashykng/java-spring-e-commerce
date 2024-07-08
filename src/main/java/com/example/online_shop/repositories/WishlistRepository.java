package com.example.online_shop.repositories;

import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import com.example.online_shop.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findByUser(User user);

    @Query("SELECT COUNT(w) > 0 FROM Wishlist w WHERE w.product = :product AND w.user = :user")
    boolean existsByProductAndUser(@Param("product") Product product, @Param("user") User user);

    Wishlist findByProductAndUser(Product product, User user);
}
