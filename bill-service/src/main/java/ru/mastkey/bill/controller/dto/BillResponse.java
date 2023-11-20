package ru.mastkey.bill.controller.dto;

import jakarta.persistence.JoinColumn;
import ru.mastkey.bill.entity.Bill;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BillResponse {
    private Long billId;

    private Long accountId;

    private BigDecimal amount;

    @JoinColumn(name = "isDefault")
    private Boolean isDefault;

    private OffsetDateTime creationDate;

    private Boolean overdraftEnable;

    public BillResponse(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, OffsetDateTime creationDate, Boolean overdraftEnable) {
        this.billId = billId;
        this.accountId = accountId;
        this.amount = amount;
        this.isDefault = isDefault;
        this.creationDate = creationDate;
        this.overdraftEnable = overdraftEnable;
    }

    public BillResponse(Bill bill) {
        this.billId = bill.getBillId();
        this.accountId = bill.getAccountId();
        this.amount = bill.getAmount();
        this.isDefault = bill.getDefault();
        this.creationDate = bill.getCreationDate();
        this.overdraftEnable = bill.getOverdraftEnable();
    }

    public BillResponse() {
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
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
}
