package com.finvault.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "split_bills")
public class SplitBill {

    @Id
    private String id;

    private String createdByUserId;
    private String title;
    private Double totalAmount;
    private List<SplitBillMember> members;
    private Double perPersonAmount;
    private String status;
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCreatedByUserId() {
        return createdByUserId;
    }
    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
      public List<SplitBillMember> getMembers() {
        return members;
    }
    public void setMembers(List<SplitBillMember> members) {
        this.members = members;
    }
    
    public Double getPerPersonAmount() {
        return perPersonAmount;
    }
    public void setPerPersonAmount(Double perPersonAmount) {
        this.perPersonAmount = perPersonAmount;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
  
    


    
}
