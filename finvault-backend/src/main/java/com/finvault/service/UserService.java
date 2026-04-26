package com.finvault.service;
import com.finvault.model.User;
import com.finvault.repository.UserRepository;


import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(String name, String email, String password){
        
        if(userRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setKycStatus("NOT_SUBMITTED");
        user.setCreatedAt(LocalDateTime.now());
    
  

        return userRepository.save(user);
    }
    public User loginUser(String email, String password){
        User user =userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found."));

                    if (!passwordEncoder.matches(password, user.getPassword())){
                        throw new RuntimeException("Invalid password");
                    }
                    return user;
    }
    
}
