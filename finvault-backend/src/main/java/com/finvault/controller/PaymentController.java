package com.finvault.controller;
import java.util.Map;
import com.finvault.model.Payment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finvault.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public Payment initiatePayment(
        Authentication authentication,
        @RequestBody Map<String, Object> request
    )
    {
        String email = authentication.getName();
        String receiverName = request.get("receiverName").toString();
        Double amount = Double.valueOf(request.get("amount").toString());
        String purpose = request.get("purpose").toString();
        String category = request.get("category").toString();

        return paymentService.initiatePayment(email, receiverName, amount, purpose, category);

    }
    @GetMapping("/my")
    public List<Payment> getMyPAyments(Authentication authentication){
        return paymentService.getMyPayments(authentication.getName());
    }

}
