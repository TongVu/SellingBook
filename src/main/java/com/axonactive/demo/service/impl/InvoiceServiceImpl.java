package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.InvoiceDetailRequest;
import com.axonactive.demo.controller.request.InvoiceRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.entity.CreditCard;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.entity.Invoice;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.repository.CreditCardRepository;
import com.axonactive.demo.repository.EbookRepository;
import com.axonactive.demo.repository.InvoiceDetailRepository;
import com.axonactive.demo.repository.InvoiceRepository;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.CreditCardService;
import com.axonactive.demo.service.InvoiceDetailService;
import com.axonactive.demo.service.InvoiceService;
import com.axonactive.demo.service.dto.invoiceDto.InvoiceDto;
import com.axonactive.demo.service.mapper.InvoiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @Autowired
    private EbookRepository ebookRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

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

        accountService.findAccountById(requestedCreditCard.getAccount().getId())
                .orElseThrow(BusinessLogicException::accountAndCreditCardNotMatch);

        updatedInvoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        updatedInvoice.setQuantity(invoiceRequest.getQuantity());

        if (invoiceRequest.isPay() &&
                requestedCreditCard.getBalance() - invoiceRequest.getTotalPayment() >= 0) {
            updatedInvoice.setPay(invoiceRequest.isPay());
            requestedCreditCard.setBalance(requestedCreditCard.getBalance() - invoiceRequest.getTotalPayment());
        } else updatedInvoice.setPay(false);

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

        if (requestedCreditCard.getAccount().getId() != invoiceRequest.getAccountId()) {
            Invoice invoice = new Invoice();
            invoice.setId(-1);

            return invoice;
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

        return invoiceRepository.saveAndFlush(createdInvoice);
    }

    @Override
    public Invoice buyEbook(Integer accountId, Integer ebookId, Integer creditCardId) {
        log.info("Searching for ebook has id {} ", ebookId);
        Ebook requestedEbook = ebookRepository.findById(ebookId)
                .orElseThrow(BusinessLogicException::ebookNotFound);

        log.info("Searching for credit card has id {} ", creditCardId);
        CreditCard requestedCreditCard = creditCardRepository.findById(creditCardId)
                .orElseThrow(BusinessLogicException::creditCardNotFound);

        log.info("Searching for account has id {} ", accountId);
        Account requestedAccount = accountService.findAccountById(accountId)
                .orElseThrow(BusinessLogicException::accountNotFound);

        InvoiceRequest invoiceRequest = new InvoiceRequest();
        invoiceRequest.setInvoiceDate(LocalDate.now());
        invoiceRequest.setQuantity(1);
        invoiceRequest.setPay(true);
        invoiceRequest.setTotalPayment(invoiceDetailService.findByEbookId(ebookId).get().getEbookPrice());
        invoiceRequest.setCreditCardId(creditCardId);
        invoiceRequest.setAccountId(accountId);

        Invoice createdInvoice = create(invoiceRequest);

        if(createdInvoice.getId() != -1){ // creating invoice has failed, don't need to create invoiceDetail
            InvoiceDetailRequest invoiceDetailRequest = new InvoiceDetailRequest();
            invoiceDetailRequest.setDateAdded(LocalDate.now());
            invoiceDetailRequest.setEbookPrice(invoiceDetailService.findByEbookId(ebookId).get().getEbookPrice());
            invoiceDetailRequest.setInvoiceId(createdInvoice.getId());
            invoiceDetailRequest.setEbookId(ebookId);

            invoiceDetailService.create(invoiceDetailRequest);
        }

        return createdInvoice;
    }
}
