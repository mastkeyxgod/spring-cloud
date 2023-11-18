package ru.mastkey.account.controller.dto;

import ru.mastkey.account.entity.Account;

import java.time.OffsetDateTime;
import java.util.List;

public class AccountResponse {
    private Long accountId;
    private String name;
    private String email;
    private String phone;
    private OffsetDateTime creationDate;
    private List<Long> bills;

    public AccountResponse(Long accountId, String name, String email, OffsetDateTime creationDate) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.creationDate = creationDate;
    }

    public AccountResponse(Account account) {
        this.accountId = account.getAccountId();
        this.name = account.getName();
        this.email = account.getEmail();
        this.phone = account.getPhone();
        this.creationDate = account.getCreationDate();
    }

    public String getPhone() {
        return phone;
    }

    public List<Long> getBills() {
        return bills;
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
