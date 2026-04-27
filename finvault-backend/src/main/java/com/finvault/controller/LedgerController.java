package com.finvault.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finvault.model.LedgerEntry;
import com.finvault.service.LedgerService;

import java.util.Map;
import java.util.List;



@RestController
@RequestMapping("/api/ledger")

public class LedgerController {
    
    @Autowired
    private LedgerService ledgerService;

    @GetMapping("/my")
    public List<LedgerEntry> getMyLedger(Authentication authentication) {
            return ledgerService.getMyLedger(authentication.getName());

    }


    @GetMapping("/summary")
    public Map<String, Double> getSummary(Authentication authentication) {
        Double totalSpent = ledgerService.getTotalSpent(authentication.getName());
        
        return Map.of("totalSpent", totalSpent);
    }

    @GetMapping("/category")
    public Map<String, Double> getCategoryWise(Authentication authentication){

        return ledgerService.getCategoryWiseSpending(authentication.getName());
    }

    @GetMapping("/monthly")
    public Map<String, Double> getMonthlySpending(Authentication authentication){

        return ledgerService.getMonthlySpending(authentication.getName());
    }
}
