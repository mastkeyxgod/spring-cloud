package ru.mastkey.bill.controller.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BillRequest {
    private Long accountId;

    private BigDecimal amount;

    private Boolean isDefault;

    private Boolean overdraftEnable;

    public BillRequest() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }


    public Boolean getOverdraftEnable() {
        return overdraftEnable;
    }

    public void setOverdraftEnable(Boolean overdraftEnable) {
        this.overdraftEnable = overdraftEnable;
    }
}
