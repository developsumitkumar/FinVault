package com.finvault.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finvault.service.KycService;
import com.finvault.service.LedgerService;
import com.finvault.service.PaymentService;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private KycService kycService;

    @Autowired
    private LedgerService ledgerService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/summary")
    public Map<String, Object> getDashboardSummary(Authentication authentication){

        String email = authentication.getName();

        Map<String, Object> response = new HashMap<>();

        response.put("kyc", kycService.getKycStatus(email));
        response.put("totalSpent", ledgerService.getTotalSpent(email));
        response.put("categoryWiseSpending", ledgerService.getCategoryWiseSpending(email));
        response.put("monthlySpending", ledgerService.getMonthlySpending(email));
        response.put("recentPayments", paymentService.getMyPayments(email));
        response.put("recentPayments", paymentService.getRecentPayments(email));
        return response;

    }
    
}
