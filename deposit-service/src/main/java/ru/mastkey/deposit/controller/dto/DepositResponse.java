package ru.mastkey.deposit.controller.dto;

import java.math.BigDecimal;

public class DepositResponse {
    private BigDecimal amount;
    private String email;

    public DepositResponse(BigDecimal amount, String email) {
        this.amount = amount;
        this.email = email;
    }

    public DepositResponse() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
