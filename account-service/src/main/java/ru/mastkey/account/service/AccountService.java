package ru.mastkey.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mastkey.account.entity.Account;
import ru.mastkey.account.exception.AccountNotFoundException;
import ru.mastkey.account.repository.AccountRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Unable to found account with id: " + id)
        );
    }

    public Long createAccount(String name, String phone, String email, List<Long> bills){
        Account account = new Account(name, email, phone, OffsetDateTime.now(),bills);
        return accountRepository.save(account).getAccountId();
    }

    public Account updateAccount(Long accountId, String name, String phone,
                                 String email, List<Long> bills) {
        Account account = new Account();
        account.setAccountId(accountId);
        account.setName(name);
        account.setEmail(email);
        account.setPhone(phone);
        account.setBills(bills);

        return accountRepository.save(account);
    }

    public Account deleteAccount(Long accountId) {
        Account account = getAccountById(accountId);
        accountRepository.deleteById(accountId);
        return account;
    }
}

