package com.mvpmatch.vending_machine.enums;

public enum Denomination {

    FIVE_CENT(5),
    TEN_CENT(10),
    TWENTY_CENT(20),
    FIFTY_CENT(50),
    ONE_HUNDRED_CENT(100);

    private Integer amount;

    Denomination(int amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
