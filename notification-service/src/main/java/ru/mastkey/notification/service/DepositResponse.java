package ru.mastkey.notification.service;

import java.math.BigDecimal;

public class DepositResponse {
    private BigDecimal amount;
    private String email;
    private Long billId;
    private BigDecimal newBalance;

    public DepositResponse(BigDecimal amount, String email, Long billId, BigDecimal newBalance) {
        this.amount = amount;
        this.email = email;
        this.billId = billId;
        this.newBalance = newBalance;
    }

    public DepositResponse() {
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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
