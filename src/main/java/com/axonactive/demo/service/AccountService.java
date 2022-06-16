package com.axonactive.demo.service;

import com.axonactive.demo.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAll();

    Account save(Account account);

    void deleteById(Integer id);

    Optional<Account> findAccountById(Integer id);

    Optional<Account> findAccountByEmailContaining(String email);

    Optional<Account> findAccountByPhone(String phone);
}
