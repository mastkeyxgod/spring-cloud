package ru.mastkey.bill.rest;

import java.time.OffsetDateTime;
import java.util.List;

public class AccountResponse {
    private Long accountId;
    private String name;
    private String email;
    private String phone;
    private OffsetDateTime creationDate;
    private List<Long> bills;

    public AccountResponse(Long accountId, String name, String email, String phone, OffsetDateTime creationDate, List<Long> bills) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.creationDate = creationDate;
        this.bills = bills;
    }

    public String getPhone() {
        return phone;
    }

    public List<Long> getBills() {
        return bills;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setBills(List<Long> bills) {
        this.bills = bills;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }
}
