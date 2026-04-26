package com.finvault.repository;

import java.util.List;
import com.finvault.model.Payment;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    List<Payment> findByUserId(String userId);

    
}
