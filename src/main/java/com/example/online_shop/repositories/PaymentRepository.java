package com.example.online_shop.repositories;

import com.example.online_shop.models.Payment;
import com.example.online_shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    public List<Payment> findByUser(User user);
    public Payment findById(int id);

    @Query("SELECT SUM(p.amount) FROM Payment p")
    Double getTotalAmount();
}
