package ru.mastkey.notification.service;

import java.math.BigDecimal;

public class BillRegistryNotify {
    private String email;
    private String name;
    private BigDecimal amount;
    private Long billId;

    public BillRegistryNotify(String email, String name, BigDecimal amount, Long billId) {
        this.email = email;
        this.name = name;
        this.amount = amount;
        this.billId = billId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public BillRegistryNotify() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
