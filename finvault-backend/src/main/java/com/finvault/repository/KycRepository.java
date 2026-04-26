package com.finvault.repository;

import com.finvault.model.Kyc;


import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

    public interface KycRepository extends MongoRepository<Kyc, String> {
    
        Optional<Kyc> findByUserId(String userId);
        
    }

