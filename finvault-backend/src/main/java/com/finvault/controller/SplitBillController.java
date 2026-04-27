package com.finvault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;

import com.finvault.model.SplitBill;
import com.finvault.service.SplitBillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/api/split-bills")
public class SplitBillController {

    @Autowired
    private SplitBillService splitBillService;

    @PostMapping
    public SplitBill createSplitBill(
            Authentication authentication,
            @RequestBody Map<String, Object> request

    )  {
            String title= request.get("title").toString();
            Double totalAmount = Double.valueOf(request.get("totalAmount").toString());

            List<String> members = (List<String>) request.get("members");

                return splitBillService.createSplitBill(

                        authentication.getName(),
                        title,
                        totalAmount,
                        members
                    );
        }
    
        @GetMapping("/my")
        public List<SplitBill> getMySplitBills(Authentication authentication) {

                return splitBillService.getMySplitBills(authentication.getName());
        }

        @PostMapping("/{id}/settle")
        public SplitBill settleSplitBill(
                Authentication authentication,
                @PathVariable String id,
                @RequestBody Map<String, String> request 
        
        )   {

            return splitBillService.settleSplitBill(
                        authentication.getName(),
                        id, 
                        request.get("receiverName")
        
                    );
            }
        
        
}
