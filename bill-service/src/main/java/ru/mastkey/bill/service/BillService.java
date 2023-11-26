package ru.mastkey.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mastkey.bill.entity.Bill;
import ru.mastkey.bill.exception.BillNotFound;
import ru.mastkey.bill.repository.BillRepository;
import ru.mastkey.bill.rest.AccountRequest;
import ru.mastkey.bill.rest.AccountResponse;
import ru.mastkey.bill.rest.AccountServiceClient;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    private final AccountServiceClient accountServiceClient;

    @Autowired
    public BillService(BillRepository billRepository, AccountServiceClient accountServiceClient) {
        this.billRepository = billRepository;
        this.accountServiceClient = accountServiceClient;
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
