package ru.mastkey.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mastkey.account.controller.dto.RegistryNotify;
import ru.mastkey.account.entity.Account;
import ru.mastkey.account.exception.AccountNotFoundException;
import ru.mastkey.account.exception.RabbitMQMessageException;
import ru.mastkey.account.repository.AccountRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AccountService {
    public static final String TOPIC_EXCHANGE_ACCOUNT_REGISTRY = "js.accountRegistry.notify.exchange";
    public static final String ROUTING_KEY_ACCOUNT_REGISTRY = "js.key.accountRegistry";

    private final AccountRepository accountRepository;

    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public AccountService(AccountRepository accountRepository, RabbitTemplate rabbitTemplate) {
        this.accountRepository = accountRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Unable to found account with id: " + id)
        );
    }

    public Long createAccount(String name, String phone, String email){
        Account account = new Account(name, email, phone, OffsetDateTime.now());

        RegistryNotify notify = new RegistryNotify(email, name);

        ObjectMapper objectMapper =new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_ACCOUNT_REGISTRY, ROUTING_KEY_ACCOUNT_REGISTRY,
                    objectMapper.writeValueAsString(notify));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RabbitMQMessageException("Can't send message to RabbitMQ");
        }


        return accountRepository.save(account).getAccountId();
    }

    public Account updateAccount(Long accountId, String name, String phone,
                                 String email, List<Long> bills) {
        Account accountOld = getAccountById(accountId);
        Account account = new Account();
        account.setAccountId(accountId);
        account.setName(name);
        account.setCreationDate(accountOld.getCreationDate());
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

