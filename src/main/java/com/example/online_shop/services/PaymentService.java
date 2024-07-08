package com.example.online_shop.services;

import com.example.online_shop.models.Payment;
import com.example.online_shop.models.User;
import com.example.online_shop.dto.PaymentDto;
import com.example.online_shop.models.Payment;
import com.example.online_shop.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findByUser(User user){
        return paymentRepository.findByUser(user);
    }

    public Payment findById(int id){
        return paymentRepository.findById(id);
    }

    public void create(Payment payment) {
        paymentRepository.save(payment);
    }
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PaymentDto convertToDto(Payment payment){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(payment.getId());
        paymentDto.setOrders(payment.getOrders());
        paymentDto.setUser(payment.getUser());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setPaidAt(payment.getPaidAt());

        return paymentDto;
    }


    public Double getTotalAmount() {
        return paymentRepository.getTotalAmount();
    }
}
