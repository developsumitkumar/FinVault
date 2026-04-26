package com.finvault.controller;

import com.finvault.dto.AuthResponse;
import com.finvault.dto.LoginRequest;
import com.finvault.dto.RegisterRequest;
import com.finvault.model.User;
import com.finvault.security.JwtService;
import com.finvault.service.UserService;
import java.util.HashMap;
import java.util.Map;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired JwtService jwtService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request){

        User savedUser= userService.registerUser(
            request.getName(),
            request.getEmail(), 
            request.getPassword()
        );
        return new AuthResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getRole(),
            savedUser.getKycStatus()
                
        );
    }

        @PostMapping("/login")
        public Map<String, Object> login(@RequestBody LoginRequest request){
            User user = userService.loginUser(
                request.getEmail(), 
                request.getPassword()
            );
            String token = jwtService.generateToken(user.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", new AuthResponse(
            
                user.getId(),
                user.getName(), 
                user.getEmail(),
                user.getRole(), 
                user.getKycStatus()
            ));
            return response;
    }

}
    
    

