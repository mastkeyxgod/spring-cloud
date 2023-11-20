package ru.mastkey.deposit.controller.dto;

import java.math.BigDecimal;

public class DepositRequest {

    private Long accountId;

    private Long billId;

    private BigDecimal amount;

    public Long getAccountId() {
        return accountId;
    }

    public Long getBillId() {
        return billId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
