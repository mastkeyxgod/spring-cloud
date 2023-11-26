package ru.mastkey.bill.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "account-service")
public interface AccountServiceClient {
    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.PUT)
    void updateAccount(@PathVariable("accountId") Long accountId, @RequestBody AccountRequest accountRequest);

    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
    AccountResponse getAccountById(@PathVariable("accountId") Long accountId);
}
