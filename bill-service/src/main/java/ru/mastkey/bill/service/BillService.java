package ru.mastkey.bill.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mastkey.bill.controller.dto.BillRegistryNotify;
import ru.mastkey.bill.entity.Bill;
import ru.mastkey.bill.exception.BillNotFound;
import ru.mastkey.bill.exception.RabbitMQMessageException;
import ru.mastkey.bill.repository.BillRepository;
import ru.mastkey.bill.rest.AccountRequest;
import ru.mastkey.bill.rest.AccountResponse;
import ru.mastkey.bill.rest.AccountServiceClient;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BillService {
    public static final String TOPIC_EXCHANGE_BILL_REGISTRY = "js.billRegistry.notify.exchange";
    public static final String ROUTING_KEY_BILL_REGISTRY = "js.key.billRegistry";

    private final BillRepository billRepository;

    private final AccountServiceClient accountServiceClient;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public BillService(BillRepository billRepository, AccountServiceClient accountServiceClient,
                       RabbitTemplate rabbitTemplate) {
        this.billRepository = billRepository;
        this.accountServiceClient = accountServiceClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(
                () -> new BillNotFound("Unable to found bill with id: " + id)
        );
    }

    public Long createBill(Long accountId, BigDecimal amount, Boolean overdraftEnable) {
        AccountResponse accountResponse = accountServiceClient.getAccountById(accountId);
        List<Long> bills = accountResponse.getBills();

        Bill bill = new Bill();

        bill.setAccountId(accountId);
        bill.setAmount(amount);
        bill.setCreationDate(OffsetDateTime.now());
        bill.setOverdraftEnable(overdraftEnable);
        if (bills.size() == 0) {

            bill.setDefault(true);

        } else {
            bill.setDefault(false);
        }

        Long billId = billRepository.save(bill).getBillId();

        bills.add(billId);

        accountServiceClient.updateAccount(accountId, new AccountRequest(accountResponse.getName(),accountResponse.getEmail(),
                accountResponse.getPhone(), bills));

        BillRegistryNotify notify = new BillRegistryNotify(accountResponse.getEmail(), accountResponse.getName(),
                amount, billId);

        ObjectMapper objectMapper =new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_BILL_REGISTRY, ROUTING_KEY_BILL_REGISTRY,
                    objectMapper.writeValueAsString(notify));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RabbitMQMessageException("Can't send message to RabbitMQ");
        }


        return billId;
    }

    public Bill updateBill(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnable) {
        Bill bill = new Bill(accountId, amount, isDefault, overdraftEnable);
        bill.setBillId(billId);
        return billRepository.save(bill);
    }

    public Bill deleteBill(Long billId) {
        Bill bill = getBillById(billId);
        billRepository.deleteById(billId);
        return bill;
    }

    public List<Bill> getBillsByAccountId(Long accountId) {
        return billRepository.getBillsByAccountId(accountId);
    }
}
