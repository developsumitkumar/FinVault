package com.finvault.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finvault.model.LedgerEntry;

public interface LedgerRepository extends MongoRepository<LedgerEntry, String >{

    List<LedgerEntry> findByUserId(String userId);
}