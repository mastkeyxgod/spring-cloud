package ru.mastkey.bill.rest;

import java.util.List;

public class AccountRequest {
    private String name;
    private String email;
    private String phone;
    private List<Long> bills;

    public AccountRequest(String name, String email, String phone, List<Long> bills) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bills = bills;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBills(List<Long> bills) {
        this.bills = bills;
    }
}
