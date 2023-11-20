package ru.mastkey.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mastkey.deposit.Entity.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
