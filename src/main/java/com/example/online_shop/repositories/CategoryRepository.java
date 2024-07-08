package com.example.online_shop.repositories;

import com.example.online_shop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByName(String name);

    public Optional<Category> findById(int id);
}
