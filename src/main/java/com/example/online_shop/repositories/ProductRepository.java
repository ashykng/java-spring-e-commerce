package com.example.online_shop.repositories;

import com.example.online_shop.models.Category;
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
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(int id);

    Product findByName(String name);

    boolean existsByNameAndCategory_Name(String name, String categoryName);

    List<Product> findBySeller_Id(int sellerId);

    List<Product> findTop3ByOrderByCreatedAtDesc();

    @Query("SELECT COUNT(p) FROM Product p")
    long countProducts();

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.name = :name, p.description = :description, p.price = :price, p.stock = :stock, p.inventory = :inventory, p.category = :category WHERE p.id = :id")
    int updateProduct(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("price") int price,
            @Param("stock") int stock,
            @Param("inventory") int inventory,
            @Param("category") Category category
    );

    void deleteByCategory(Category category);

    void deleteBySeller(User seller);


    @Query("SELECT COUNT(p) FROM Product p WHERE p.seller = :seller")
    public int countBySeller(@Param("seller") User seller);
}

