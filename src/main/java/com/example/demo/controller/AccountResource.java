package com.example.demo.controller;

import com.example.demo.controller.request.AccountRequest;
import com.example.demo.entity.Account;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.AccountService;
import com.example.demo.service.dto.accountDto.AccountDto;
import com.example.demo.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AccountResource.PATH)
public class AccountResource {
    public static final String PATH = "/api/accounts";

    @Autowired
    AccountService accountService;
    @Autowired
    AccountMapper accountMapper;

    /*
    @Inject // to use this we have to add CDI dependency
    AccountMapper accountMapper;
     */

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        return ResponseEntity.ok(accountMapper.toDtos(accountService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(value = "id") Integer id) throws Exception {
        Account account = accountService.findAccountById(id).orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
        return ResponseEntity.ok(accountMapper.toDto(account));
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountRequest newAccount) {
        Account createdAccount = new Account();
        createdAccount.setFirstName(newAccount.getFirstName());
        createdAccount.setLastName(newAccount.getLastName());
        createdAccount.setDob(newAccount.getDob());
        createdAccount.setGender(newAccount.getGender());
        createdAccount.setEmail(newAccount.getEmail());
        createdAccount.setPhone(newAccount.getPhone());
        createdAccount.setAddress(newAccount.getAddress());

        accountService.save(createdAccount);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdAccount.getId()))
                .body(accountMapper.toDto(createdAccount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Integer id,
                                                    @RequestBody AccountRequest account) throws ResourceNotFoundException {
        Account updatedAccount = accountService.findAccountById(id).orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        updatedAccount.setFirstName(account.getFirstName());
        updatedAccount.setLastName(account.getLastName());
        updatedAccount.setDob(account.getDob());
        updatedAccount.setGender(account.getGender());
        updatedAccount.setEmail(account.getEmail());
        updatedAccount.setPhone(account.getPhone());
        updatedAccount.setAddress(account.getAddress());

        return ResponseEntity.ok(accountMapper.toDto(
                accountService.save(updatedAccount)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Account deletedAccount = accountService.findAccountById(id).orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
