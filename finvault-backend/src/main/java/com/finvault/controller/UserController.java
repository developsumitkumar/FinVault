package com.finvault.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/user")

public class UserController {

        @GetMapping("/me")
        public String getLoggedInUser(Authentication authentication){
            return "Loggen in as : " +authentication.getName();
     }
        }
        

