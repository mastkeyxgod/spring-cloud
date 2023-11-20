package ru.mastkey.deposit.rest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BillRequest {
    private Long accountId;

    private BigDecimal amount;

    private Boolean isDefault;

    private Boolean overdraftEnable;

    private OffsetDateTime creationDate;

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }


    public Boolean getOverdraftEnable() {
        return overdraftEnable;
    }

    public void setOverdraftEnable(Boolean overdraftEnable) {
        this.overdraftEnable = overdraftEnable;
    }
}
