package ru.mastkey.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mastkey.bill.entity.Bill;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> getBillsByAccountId(Long accountId);
}
