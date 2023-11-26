package ru.mastkey.deposit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mastkey.deposit.Entity.Deposit;
import ru.mastkey.deposit.controller.dto.DepositResponse;
import ru.mastkey.deposit.exception.DepositServiceException;
import ru.mastkey.deposit.repository.DepositRepository;
import ru.mastkey.deposit.rest.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class DepositService {
    public static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    public static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final DepositRepository depositRepository;

    private final AccountServiceClient accountServiceClient;

    private final BillServiceClient billServiceClient;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public DepositService(DepositRepository depositRepository, AccountServiceClient accountServiceClient,
                          BillServiceClient billServiceClient, RabbitTemplate rabbitTemplate) {
        this.depositRepository = depositRepository;
        this.accountServiceClient = accountServiceClient;
        this.billServiceClient = billServiceClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public DepositResponse deposit(Long accountId, Long billId, BigDecimal amount) {
        if (accountId == null && billId == null) {
            throw new DepositServiceException("Account and bill is null");
        }

        if (billId != null) {
            BillResponse billResponse = billServiceClient.getBillById(billId);
            BillRequest billRequest = createBillRequest(amount, billResponse);

            billServiceClient.update(billId, billRequest);

            String email = accountServiceClient
                    .getAccountById(billResponse.getAccountId())
                    .getEmail();

            depositRepository.save(new Deposit(amount, billId, OffsetDateTime.now(), email));

            return getDepositResponseAndSendMessage(amount, email);
        }
        BillResponse defaultBill = getDefaultBill(accountId);
        BillRequest billRequest = createBillRequest(amount, defaultBill);
        billServiceClient.update(defaultBill.getBillId(), billRequest);
        AccountResponse account = accountServiceClient.getAccountById(accountId);

        depositRepository.save(new Deposit(amount, defaultBill.getBillId(), OffsetDateTime.now(), account.getEmail()));

        return getDepositResponseAndSendMessage(amount, account.getEmail());
    }

    private DepositResponse getDepositResponseAndSendMessage(BigDecimal amount, String email) {
        DepositResponse depositResponse = new DepositResponse(amount, email);

        ObjectMapper objectMapper =new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT, objectMapper.writeValueAsString(depositResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new DepositServiceException("Can't send message to RabbitMQ");
        }
        return depositResponse;
    }

    private static BillRequest createBillRequest(BigDecimal amount, BillResponse billResponse) {
        BillRequest billRequest = new BillRequest();
        billRequest.setAccountId(billResponse.getAccountId());
        billRequest.setIsDefault(billResponse.getIsDefault());
        billRequest.setOverdraftEnable(billResponse.getOverdraftEnable());
        billRequest.setCreationDate(billResponse.getCreationDate());
        billRequest.setAmount(billResponse.getAmount().add(amount));
        return billRequest;
    }


    private BillResponse getDefaultBill(Long accountId) {
        return billServiceClient.getBillsByAccountId(accountId)
                .stream()
                .filter(BillResponse::getIsDefault)
                .findAny()
                .orElseThrow(
                        () -> new DepositServiceException("unable")
                );
    }
}
