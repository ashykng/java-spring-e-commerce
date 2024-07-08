package com.example.online_shop.repositories;

import com.example.online_shop.models.Cart;
import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUser(User user);

    Cart findByProductAndUser(Product product, User user);

    @Query("SELECT COUNT(c) > 0 FROM Cart c WHERE c.product = :product AND c.user = :user")
    boolean existsByProductAndUser(@Param("product") Product product, @Param("user") User user);

    @Query("SELECT SUM(c.product.price * c.quantity) FROM Cart c WHERE c.user = :user")
    Double getTotalPriceByUser(@Param("user") User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.user = :user")
    void deleteAllByUser(@Param("user") User user);
}
