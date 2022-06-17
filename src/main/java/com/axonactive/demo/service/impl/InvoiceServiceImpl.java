package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.InvoiceRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.entity.CreditCard;
import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.repository.InvoiceRepository;
import com.axonactive.demo.entity.Invoice;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.CreditCardService;
import com.axonactive.demo.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Invoice> findInvoiceById(Integer id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice create(InvoiceRequest invoiceRequest) throws ResourceNotFoundException {
        CreditCard requestedCreditCard = creditCardService.findCreditCardById(invoiceRequest.getCreditCardId())
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found " + invoiceRequest.getCreditCardId()));

        Account requestedAccount = accountService.findAccountById(invoiceRequest.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found " + invoiceRequest.getAccountId()));

        if(requestedCreditCard.getAccount().getId() != requestedAccount.getId()) {
            return new Invoice();
        }

        Invoice createdInvoice = new Invoice();
        createdInvoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        createdInvoice.setQuantity(invoiceRequest.getQuantity());

        if(invoiceRequest.isPay() &&
                requestedCreditCard.getBalance() - invoiceRequest.getTotalPayment() >= 0 ){
            createdInvoice.setPay(invoiceRequest.isPay());
            requestedCreditCard.setBalance(requestedCreditCard.getBalance() - invoiceRequest.getTotalPayment());
        } else createdInvoice.setPay(false);

        createdInvoice.setTotalPayment(invoiceRequest.getTotalPayment());
        createdInvoice.setCreditCard(requestedCreditCard);
        createdInvoice.setAccount(requestedAccount);

        return createdInvoice;
    }
}
