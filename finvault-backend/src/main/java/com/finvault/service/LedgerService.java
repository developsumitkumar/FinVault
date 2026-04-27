package com.finvault.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finvault.model.LedgerEntry;
import com.finvault.model.Payment;
import com.finvault.repository.*;
import com.finvault.model.User;

@Service
public class LedgerService {
    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private UserRepository userRepository;

    public LedgerEntry createEntryForPayment(Payment payment){

        LedgerEntry entry = new LedgerEntry();

        entry.setUserId(payment.getUserId());
        entry.setPaymentId(payment.getId());
        entry.setType("DEBIT");
        entry.setAmount(payment.getAmount());
        entry.setCategory(payment.getCategory());
        entry.setDescription("Payment to "+ payment.getReceiverName());
        entry.setCreatedAt(LocalDateTime.now());

        return ledgerRepository.save(entry);

    }

    public List<LedgerEntry> getMyLedger(String email) {

        User user = userRepository.findByEmail(email)
                    .orElseThrow( () -> new RuntimeException("User not Found." ));
            
                    return ledgerRepository.findByUserId(user.getId());
            
    }
        public Double getTotalSpent(String email) {

            User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found."));

                        return ledgerRepository.findByUserId(user.getId())
                                .stream()
                                .filter(entry -> "DEBIT".equals(entry.getType()))
                                .mapToDouble(LedgerEntry::getAmount)
                                .sum();
        }

        public Map<String,Double> getCategoryWiseSpending(String email){

            User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User Not Found."));

                        return ledgerRepository.findByUserId(user.getId())
                                .stream()
                                .filter(entry -> "DEBIT".equals(entry.getType()))
                                .collect(Collectors.groupingBy(
                                LedgerEntry::getCategory,
                                Collectors.summingDouble(LedgerEntry::getAmount)
                                    
                                ));
        }

        public Map<String, Double> getMonthlySpending(String email) {

            User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User Not Found"));

                        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM");

                        return ledgerRepository.findByUserId((user.getId()))
                                .stream()
                                .filter(entry -> "DEBIT".equals(entry.getType()))
                                .collect(Collectors.groupingBy(
                                    entry -> entry.getCreatedAt().format(formatter),
                                    Collectors.summingDouble(LedgerEntry::getAmount)
                        

                                ));
        }
}
