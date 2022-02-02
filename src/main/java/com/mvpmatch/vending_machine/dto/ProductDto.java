package com.mvpmatch.vending_machine.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long cost;

    private Long amountAvailable;

    private UserDto createdBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Long amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public UserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDto createdBy) {
        this.createdBy = createdBy;
    }
}
