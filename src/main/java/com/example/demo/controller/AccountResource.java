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
    public ResponseEntity<List<AccountDto>> getAll(){
        return ResponseEntity.ok(accountMapper.toDtos(accountService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(value = "id") Integer id) throws Exception{
        Account account = accountService.findAccountById(id).orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
        return ResponseEntity.ok(accountMapper.toDto(account));
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountRequest newAccount){
        Account createdAccount = accountService.save(
                new Account(
                        null,
                        newAccount.getFirstName(),
                        newAccount.getLastName(),
                        newAccount.getDob(),
                        newAccount.getGender(),
                        newAccount.getEmail(),
                        newAccount.getPhone(),
                        newAccount.getAddress()
                )
        );

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdAccount.getId()))
                .body(accountMapper.toDto(createdAccount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Integer id,
                                                    @RequestBody AccountRequest account) throws ResourceNotFoundException{
        Account updatedAccount = accountService.findAccountById(id).orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        updatedAccount.setAddress(account.getAddress());
        updatedAccount.setDob(account.getDob());
        updatedAccount.setEmail(account.getEmail());
        updatedAccount.setGender(account.getGender());
        updatedAccount.setFirstName(account.getFirstName());
        updatedAccount.setLastName(account.getLastName());
        accountService.save(updatedAccount);

        return ResponseEntity.ok(accountMapper.toDto(updatedAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id)  throws ResourceNotFoundException{
        Account deletedAccount = accountService.findAccountById(id).orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
