package com.example.online_shop.repositories;

import com.example.online_shop.models.Order;
import com.example.online_shop.models.Payment;
import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    List<Order> findByUser(User user);

    List<Order> findByUserAndPayment(User user, Payment payment);

    Order findByProductAndUser(Product product, User user);

    @Query("SELECT SUM(o.product.price * o.quantity) FROM Order o WHERE o.product.seller = :seller")
    Double calculateTotalSalesAmount(@Param("seller") User seller);

    @Query("SELECT SUM(o.quantity) FROM Order o WHERE o.product.seller = :seller")
    Long calculateTotalProductsSold(@Param("seller") User seller);
}
