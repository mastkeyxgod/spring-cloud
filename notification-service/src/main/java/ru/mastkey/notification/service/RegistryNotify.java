package ru.mastkey.notification.service;

public class RegistryNotify {
    private String email;
    private String name;

    public RegistryNotify(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public RegistryNotify() {
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
}
