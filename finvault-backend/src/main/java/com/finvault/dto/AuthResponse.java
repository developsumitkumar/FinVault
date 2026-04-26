package com.finvault.dto;

public class AuthResponse {
    
    private String id;
    private String name;
    private String email;
    private String role;
    private String kycStatus;

    public AuthResponse(String id, String name, String email, String role, String kycStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.kycStatus = kycStatus;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    
}
