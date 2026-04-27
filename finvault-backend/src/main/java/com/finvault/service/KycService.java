package com.finvault.service;

import com.finvault.model.Kyc;
import com.finvault.model.User;
import com.finvault.repository.KycRepository;
import com.finvault.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KycService {

    @Autowired 
    private KycRepository kycRepository;

    @Autowired
    private UserRepository userRepository;

    public Kyc submitKyc(String userEmail, String docType, String docNumber ){
        User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            if(kycRepository.findByUserId(user.getId()).isPresent()){
                throw new RuntimeException("KYC already submitted.");
            }

            Kyc kyc = new Kyc();
            kyc.setUserId(user.getId());
            kyc.setDocumentType(docType);
            kyc.setDocumentNumber(docNumber);
            kyc.setStatus("PENDING");

            return kycRepository.save(kyc);
    }
    public Kyc getKycStatus(String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return kycRepository.findByUserId(user.getId())
            .orElseThrow(() -> new RuntimeException("KYC not submitted"));
}
///////////approve
   public Kyc approveKyc(String kycId) {

    Kyc kyc = kycRepository.findById(kycId)
            .orElseThrow(() -> new RuntimeException("KYC not found"));

    kyc.setStatus("VERIFIED");

    User user = userRepository.findById(kyc.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

    user.setKycStatus("VERIFIED");
    userRepository.save(user);

    return kycRepository.save(kyc);
}


//////////reject
    public Kyc rejectKyc(String kycId, String reason){
        
        Kyc kyc = kycRepository.findById(kycId)
                    .orElseThrow(() -> new RuntimeException("KYC not found."));
                    kyc.setStatus("REJECTED");
                    kyc.setRemarks(reason);

            User user = userRepository.findById(kyc.getUserId())
                        .orElseThrow(() -> new RuntimeException("User Not Found."));
                    
                    user.setKycStatus("REJECTED");
                    userRepository.save(user);
                    
                    
        return kycRepository.save(kyc);
    }

}
