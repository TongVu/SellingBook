package com.axonactive.demo.repository;

import com.axonactive.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByEmailContaining(String email);

    Optional<Account> findAccountByPhone(String phone);
}
