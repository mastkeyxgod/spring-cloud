package ru.mastkey.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mastkey.bill.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
