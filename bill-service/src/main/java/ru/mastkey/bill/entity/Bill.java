package ru.mastkey.bill.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;

    private Long accountId;

    private BigDecimal amount;

    private Boolean isDefault;

    private OffsetDateTime creationDate;

    private Boolean overdraftEnable;

    public Bill(Long accountId, BigDecimal amount, Boolean isDefault, OffsetDateTime creationDate, Boolean overdraftEnable) {
        this.accountId = accountId;
        this.amount = amount;
        this.isDefault = isDefault;
        this.creationDate = creationDate;
        this.overdraftEnable = overdraftEnable;
    }

    public Bill(Long accountId, BigDecimal amount, Boolean isDefault,  Boolean overdraftEnable) {
        this.accountId = accountId;
        this.amount = amount;
        this.isDefault = isDefault;
        this.overdraftEnable = overdraftEnable;
    }

    public Bill() {
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getOverdraftEnable() {
        return overdraftEnable;
    }

    public void setOverdraftEnable(Boolean overdraftEnable) {
        this.overdraftEnable = overdraftEnable;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", isDefault=" + isDefault +
                ", creationDate=" + creationDate +
                ", overdraftEnable=" + overdraftEnable +
                '}';
    }
}
