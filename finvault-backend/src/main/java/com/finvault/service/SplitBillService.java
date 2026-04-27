package com.finvault.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finvault.model.SplitBill;
import com.finvault.model.SplitBillMember;

import com.finvault.repository.SplitBillRepository;
import com.finvault.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.finvault.model.User;


@Service
public class SplitBillService {

    @Autowired
    private SplitBillRepository splitBillRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentService paymentService;

    public SplitBill createSplitBill(String email, String title, Double totalAmount, List<String> members){

            User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found."));

            int totalPeople = members.size() + 1;

            List<SplitBillMember> memberList = members.stream().map(name ->{

                SplitBillMember member = new SplitBillMember();
                member.setName(name);
                member.setStatus("PENDING");

                    return member;
            }).collect(Collectors.toList());//read this also 

            SplitBill splitBill = new SplitBill();
            splitBill.setCreatedByUserId(user.getId());
            splitBill.setTitle(title);
            splitBill.setTotalAmount(totalAmount);
            splitBill.setMembers(memberList);//about this line please read carefully 
            splitBill.setPerPersonAmount(totalAmount / totalPeople);
            splitBill.setStatus("PENDING");
            splitBill.setCreatedAt(LocalDateTime.now());


            return splitBillRepository.save(splitBill);
         }

    public List<SplitBill> getMySplitBills(String email){

        User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User Not Found."));

                    return splitBillRepository.findByCreatedByUserId(user.getId());
    }   

        public SplitBill settleSplitBill(String email, String splitBillId, String receiverName){

                  SplitBill splitBill = splitBillRepository.findById(splitBillId)
                                        .orElseThrow(() -> new RuntimeException("Split Bill Not Found. "));

                   if ("SETTLED".equals(splitBill.getStatus())){
                        throw new RuntimeException("Split Bill Already Settled");
                    }                     
                    
                    //make payment
                    paymentService.initiatePayment(
                        email,
                        receiverName,
                         splitBill.getPerPersonAmount(),
                        "Settlment for split bill: " + splitBill.getTitle(),
                        "SPLIT_BILL"

                    );

                    // mark this member settled
                    for (SplitBillMember member : splitBill.getMembers()){

                            if (member.getName().equalsIgnoreCase(receiverName)){

                                member.setStatus("SETTLED");
                            }

                    }

                    //check if all member settled
                    boolean allSettled = splitBill.getMembers()
                    .stream()
                    .allMatch(member -> "SETTLED".equals(member.getStatus()));

                    //if yes mark whole bill settled
                    if (allSettled){
                        splitBill.setStatus("SETTLED");
                    }
                    return splitBillRepository.save(splitBill);

        }
    
}
