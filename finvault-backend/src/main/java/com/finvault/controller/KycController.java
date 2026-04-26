package com.finvault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finvault.service.KycService;
import com.finvault.model.Kyc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RestController
@RequestMapping("/api/kyc")

public class KycController {

    @Autowired
    private KycService kycService;

    @PostMapping("/submit")
    public Kyc submitKyc(
        Authentication authentication,
        @RequestBody Map<String, String> request 

    ){
        String email = authentication.getName();

        return kycService.submitKyc(email, 
            request.get("documentType"),
            request.get("documentNumber")
    
        );
    }
    @GetMapping("/status")
    public Kyc getStatus(Authentication authentication) {

    String email = authentication.getName();

    return kycService.getKycStatus(email);
}
}
    

