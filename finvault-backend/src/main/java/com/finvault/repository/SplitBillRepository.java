package com.finvault.repository;

import com.finvault.model.SplitBill;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface  SplitBillRepository extends MongoRepository<SplitBill, String > {

    List<SplitBill> findByCreatedByUserId(String createdByUserId);
    
}
