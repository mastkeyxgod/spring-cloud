package ru.mastkey.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mastkey.bill.entity.Bill;
import ru.mastkey.bill.exception.BillNotFound;
import ru.mastkey.bill.repository.BillRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(
                () -> new BillNotFound("Unable to found bill with id: " + id)
        );
    }

    public Long createBill(Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnable) {
        return billRepository.save(new Bill(accountId, amount, isDefault,
                OffsetDateTime.now(), overdraftEnable))
                .getBillId();
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
