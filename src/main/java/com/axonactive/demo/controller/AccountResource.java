package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.AccountRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.dto.accountDto.AccountDto;
import com.axonactive.demo.service.dto.accountDto.AccountInvocesDto;
import com.axonactive.demo.service.dto.ebookDto.EbookPurchasedDto;
import com.axonactive.demo.service.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
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
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(value = "id") Integer id) {
        log.info("Searching for account has id {} ", id);
        Account account = accountService.findAccountById(id).orElseThrow(BusinessLogicException::accountNotFound);

        return ResponseEntity.ok(accountMapper.toDto(account));
    }

    @GetMapping("/{id}/purchasedbooks")
    public ResponseEntity<List<EbookPurchasedDto>> getPurcharsedEbooks(@PathVariable("id") Integer id,
                                                                       @RequestParam(value = "paid") Boolean isPay) {
        log.info("Searching for account has id {} ", id);
        Account account = accountService.findAccountById(id).orElseThrow(BusinessLogicException::accountNotFound);

        if (isPay)
            return ResponseEntity.ok(accountService.findPurchasedEbooks(id));

        return ResponseEntity.ok(accountService.findNotPurchasedEbooks(id));
    }

    @GetMapping("/{id}/invoices")
    public ResponseEntity<List<AccountInvocesDto>> getAllInvoices(@PathVariable("id") Integer id) {
        log.info("Searching for account has id {} ", id);
        Account account = accountService.findAccountById(id).orElseThrow(BusinessLogicException::accountNotFound);

        return ResponseEntity.ok(accountService.findAllInvoices(id));
    }

    @GetMapping("/find")
    public ResponseEntity<?> getAccountByEmailLikeOrByPhone(@RequestParam(value = "phone", defaultValue = "empty", required = false) String phone,
                                                            @RequestParam(value = "email", defaultValue = "empty", required = false) String email) {
        if (!phone.equalsIgnoreCase("empty"))
            return ResponseEntity.ok(accountMapper.toDto(accountService.findAccountByPhone(phone).get()));

        if (!email.equalsIgnoreCase("empty"))
            return ResponseEntity.ok(accountMapper.toDto(accountService.findAccountByEmailContaining(email).get()));

        return ResponseEntity.ok("Please provide Request param");
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountRequest accountRequest) {
        Account createdAccount = accountService.save(accountRequest);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdAccount.getId()))
                .body(accountMapper.toDto(createdAccount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Integer id,
                                                    @RequestBody AccountRequest account) {
        log.info("Searching for account has id {} ", id);
        Account updatedAccount = accountService.findAccountById(id).orElseThrow(BusinessLogicException::accountNotFound);

        accountService.update(updatedAccount, account);
        return ResponseEntity.ok(accountMapper.toDto(
                accountService.save(updatedAccount)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        log.info("Searching for account has id {} ", id);
        Account deletedAccount = accountService.findAccountById(id).orElseThrow(BusinessLogicException::accountNotFound);

        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
