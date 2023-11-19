package ru.mastkey.account.controller.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class AccountRequest {

    private String name;
    private String email;
    private String phone;
    private List<Long> bills;

    public AccountRequest() {

    }

    public String getName() {
        return name;
    }

    public List<Long> getBills() {
        return bills;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}