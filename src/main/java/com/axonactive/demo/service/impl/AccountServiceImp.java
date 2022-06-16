package com.axonactive.demo.service.impl;

import com.axonactive.demo.repository.AccountRepository;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> findAccountByEmailContaining(String email) {
        return accountRepository.findAccountByEmailContaining(email);
    }

    @Override
    public Optional<Account> findAccountByPhone(String phone) {
        return accountRepository.findAccountByPhone(phone);
    }
}
