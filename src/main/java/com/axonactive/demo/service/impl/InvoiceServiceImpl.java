package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.InvoiceRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.entity.CreditCard;
import com.axonactive.demo.entity.Invoice;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.repository.InvoiceRepository;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.CreditCardService;
import com.axonactive.demo.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private AccountService accountService;

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(Integer id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Invoice update(Invoice updatedInvoice, InvoiceRequest invoiceRequest) {
        log.info("Searching for account has id {} ", invoiceRequest.getAccountId());
        Account requestedAccount = accountService.findAccountById(invoiceRequest.getAccountId())
                .orElseThrow(BusinessLogicException::accountNotFound);

        log.info("Searching for credit card has id {} ", invoiceRequest.getCreditCardId());
        CreditCard requestedCreditCard = creditCardService.findCreditCardById(invoiceRequest.getCreditCardId())
                .orElseThrow(BusinessLogicException::creditCardNotFound);

        updatedInvoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        updatedInvoice.setQuantity(invoiceRequest.getQuantity());
        updatedInvoice.setPay(invoiceRequest.isPay());
        updatedInvoice.setTotalPayment(invoiceRequest.getTotalPayment());
        updatedInvoice.setCreditCard(requestedCreditCard);
        updatedInvoice.setAccount(requestedAccount);

        return invoiceRepository.save(updatedInvoice);
    }

    @Override
    public Optional<Invoice> findInvoiceById(Integer id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice create(InvoiceRequest invoiceRequest) {
        log.info("Searching for credit card has id {} ", invoiceRequest.getCreditCardId());
        CreditCard requestedCreditCard = creditCardService.findCreditCardById(invoiceRequest.getCreditCardId())
                .orElseThrow(BusinessLogicException::creditCardNotFound);

        log.info("Searching for account has id {} ", invoiceRequest.getAccountId());
        Account requestedAccount = accountService.findAccountById(invoiceRequest.getAccountId())
                .orElseThrow(BusinessLogicException::accountNotFound);

        if (requestedCreditCard.getAccount().getId() != requestedAccount.getId()) {
            return new Invoice();
        }

        Invoice createdInvoice = new Invoice();
        createdInvoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        createdInvoice.setQuantity(invoiceRequest.getQuantity());

        if (invoiceRequest.isPay() &&
                requestedCreditCard.getBalance() - invoiceRequest.getTotalPayment() >= 0) {
            createdInvoice.setPay(invoiceRequest.isPay());
            requestedCreditCard.setBalance(requestedCreditCard.getBalance() - invoiceRequest.getTotalPayment());
        } else createdInvoice.setPay(false);

        createdInvoice.setTotalPayment(invoiceRequest.getTotalPayment());
        createdInvoice.setCreditCard(requestedCreditCard);
        createdInvoice.setAccount(requestedAccount);

        return createdInvoice;
    }
}
