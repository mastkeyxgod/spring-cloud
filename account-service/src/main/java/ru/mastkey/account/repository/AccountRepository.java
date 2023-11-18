package ru.mastkey.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mastkey.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
