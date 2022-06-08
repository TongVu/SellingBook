package com.example.demo.service;

import com.example.demo.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAll();

    void save(Account account);

    void deleteById(Integer id);

    Optional<Account> findAccountById(Integer id);

}
