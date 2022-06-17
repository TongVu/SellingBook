package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.AccountRequest;
import com.axonactive.demo.repository.AccountRepository;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.service.dto.accountDto.AccountInvocesDto;
import com.axonactive.demo.service.dto.ebookDto.EbookPurchasedDto;
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
    public Account save(AccountRequest accountRequest) {
        Account createdAccount = new Account();
        createdAccount.setFirstName(accountRequest.getFirstName());
        createdAccount.setLastName(accountRequest.getLastName());
        createdAccount.setDob(accountRequest.getDob());
        createdAccount.setGender(accountRequest.getGender());
        createdAccount.setEmail(accountRequest.getEmail());
        createdAccount.setPhone(accountRequest.getPhone());
        createdAccount.setAddress(accountRequest.getAddress());

        accountRepository.save(createdAccount);
        return createdAccount;
    }

    @Override
    public void update(Account updatedAccount, AccountRequest account) {
        updatedAccount.setFirstName(account.getFirstName());
        updatedAccount.setLastName(account.getLastName());
        updatedAccount.setDob(account.getDob());
        updatedAccount.setGender(account.getGender());
        updatedAccount.setEmail(account.getEmail());
        updatedAccount.setPhone(account.getPhone());
        updatedAccount.setAddress(account.getAddress());
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

    @Override
    public List<EbookPurchasedDto> findPurchasedEbooks(Integer id) {
        return accountRepository.findPurchasedEbooks(id);
    }

    @Override
    public List<EbookPurchasedDto> findNotPurchasedEbooks(Integer id) {
        return accountRepository.findNotPurchasedEbooks(id);
    }

    @Override
    public List<AccountInvocesDto> findAllInvoices(Integer id) {
        return accountRepository.findAllInvoices(id);
    }
}
