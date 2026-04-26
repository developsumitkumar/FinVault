package com.finvault.controller;

import java.util.Map;
import com.finvault.model.Kyc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finvault.service.KycService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @Autowired
    private KycService kycService;

    @PostMapping("/kyc/approve")
    public Kyc approve(@RequestBody Map<String, String> request){
        System.out.println("KYC ID received: " + request.get("kycId"));

        return kycService.approveKyc(request.get("kycId"));
        
    }

    @PostMapping("/kyc/reject")
        public Kyc reject(@RequestBody Map<String, String> request ){

            return kycService.rejectKyc(
                request.get("kycId"),
                request.get("reason")
            );
        }
      
    }
    


