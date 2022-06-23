package com.axonactive.demo.service;

import com.axonactive.demo.controller.request.AccountRequest;
import com.axonactive.demo.controller.request.InvoiceRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.service.dto.accountDto.AccountInvoicesDto;
import com.axonactive.demo.service.dto.ebookDto.EbookPurchasedDto;
import com.axonactive.demo.service.dto.invoiceDto.InvoiceDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAll();

    Account save(Account account);

    Account save(AccountRequest accountRequest);

    Account update(Account account, AccountRequest accountRequest);

    void deleteById(Integer id);

    Optional<Account> findAccountById(Integer id);

    Optional<Account> findAccountByEmailContaining(String email);

    Optional<Account> findAccountByPhone(String phone);

    List<EbookPurchasedDto> findPurchasedEbooks(Integer id);

    List<EbookPurchasedDto> findNotPurchasedEbooks(Integer id);

    List<AccountInvoicesDto> findAllInvoices(Integer id);

}
