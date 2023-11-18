package ru.mastkey.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mastkey.account.controller.dto.AccountRequest;
import ru.mastkey.account.controller.dto.AccountResponse;
import ru.mastkey.account.service.AccountService;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccount(@PathVariable Long id) {
        return new AccountResponse(accountService.getAccountById(id));
    }

    @PostMapping("/")
    public Long createAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.createAccount(accountRequest.getName(), accountRequest.getPhone(),
                accountRequest.getEmail(), accountRequest.getBills());
    }

    @PutMapping("/{accountId}")
    private AccountResponse updateAccount(@PathVariable Long accountId, @RequestBody AccountRequest accountRequest) {
        return new AccountResponse(accountService.updateAccount(accountId, accountRequest.getName(), accountRequest.getEmail(),
                accountRequest.getPhone(), accountRequest.getBills())
        );
    }

    @DeleteMapping("/{accountId}")
    public AccountResponse deleteAccount(@PathVariable Long accountId) {
        return new AccountResponse(accountService.deleteAccount(accountId));
    }
}
