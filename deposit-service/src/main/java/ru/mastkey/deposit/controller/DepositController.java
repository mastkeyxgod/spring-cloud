package ru.mastkey.deposit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mastkey.deposit.Entity.Deposit;
import ru.mastkey.deposit.controller.dto.DepositRequest;
import ru.mastkey.deposit.controller.dto.DepositResponse;
import ru.mastkey.deposit.service.DepositService;

@RestController
public class DepositController {

    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/")
    public DepositResponse deposit(@RequestBody DepositRequest depositRequest) {
        return depositService.deposit(depositRequest.getAccountId(),
                depositRequest.getBillId(), depositRequest.getAmount());
    }
}
