package com.finvault.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finvault.model.Payment;
import com.finvault.model.User;
import com.finvault.repository.PaymentRepository;
import com.finvault.repository.UserRepository;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LedgerService ledgerService;

    public Payment initiatePayment(String userEmail, String receiverName, Double amount, String purpose, String category){

        User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found."));
               
                if(!"VERIFIED".equals(user.getKycStatus())){
                        throw new RuntimeException("Payment blocked: KYC not verified.");
                    }    

                Payment payment = new Payment();
                payment.setUserId(user.getId());
                payment.setReceiverName(receiverName);
                payment.setAmount(amount);     
                payment.setPurpose(purpose);
                payment.setCategory(category);
                payment.setStatus("SUCCESS");
                payment.setCreatedAt(LocalDateTime.now());
                
                Payment savedPayment = paymentRepository.save(payment);
                ledgerService.createEntryForPayment(savedPayment);
                return savedPayment;
        }
            public List <Payment> getMyPayments(String userEmail){

                User user = userRepository.findByEmail(userEmail)
                            .orElseThrow(() -> new RuntimeException("User not found."));

                            return paymentRepository.findByUserId((user.getId()));  
            }

            public List<Payment> getRecentPayments(String email){

                return getMyPayments(email)
                        .stream()
                        .sorted((a,b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                        .limit(5)
                        .toList();
            }
}
