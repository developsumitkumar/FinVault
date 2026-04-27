package com.finvault.controller;

import com.finvault.model.User;
import com.finvault.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @Autowired
    private UserRepository userRepository;

   
    @GetMapping 
    public String test(){
        return "Backend is Running";
    }
    
    @PostMapping("/save")
    public User saveUser(){
        User user =new User();
        user.setName("Sumit Test");
        return userRepository.save(user);
    }
}
