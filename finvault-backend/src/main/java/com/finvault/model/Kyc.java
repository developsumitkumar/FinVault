package com.finvault.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kyc_docs")
public class Kyc {
    
    @Id
    private String id;

    private String userId;
    private String documentType;
    private String documentNumber;
    private String status;
    private String remarks;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDocumentType() {
        return documentType;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    }
    
    

