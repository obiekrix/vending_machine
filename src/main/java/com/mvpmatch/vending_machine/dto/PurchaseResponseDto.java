package com.mvpmatch.vending_machine.dto;

public class PurchaseResponseDto {
    
    private Long totalSpent;

    private String productPurchased;

    private Long remainingDeposit;

    public Long getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Long totalSpent) {
        this.totalSpent = totalSpent;
    }

    public String getProductPurchased() {
        return productPurchased;
    }

    public void setProductPurchased(String productPurchased) {
        this.productPurchased = productPurchased;
    }

    public Long getRemainingDeposit() {
        return remainingDeposit;
    }

    public void setRemainingDeposit(Long remainingDeposit) {
        this.remainingDeposit = remainingDeposit;
    }
}
