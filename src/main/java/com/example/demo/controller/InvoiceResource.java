package com.example.demo.controller;

import com.example.demo.controller.request.InvoiceRequest;
import com.example.demo.entity.Account;
import com.example.demo.entity.CreditCard;
import com.example.demo.entity.Invoice;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.AccountService;
import com.example.demo.service.CreditCardService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.dto.invoiceDto.InvoiceDto;
import com.example.demo.service.mapper.InvoiceMapper;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(InvoiceResource.PATH)
public class InvoiceResource {
    public static final String PATH = "/api/invoices";

    @Autowired
    InvoiceMapper invoiceMapper;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAll(){
        return ResponseEntity.ok(invoiceMapper.toDtos(invoiceService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Invoice foundInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found " + id));

        return ResponseEntity.ok(invoiceMapper.toDto(foundInvoice));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDto> update(@PathVariable("id") Integer id,
                                             @RequestBody InvoiceRequest invoiceRequest) throws ResourceNotFoundException {
        Account requestedAccount = accountService.findAccountById(invoiceRequest.getAccountId())
                .orElseThrow(() -> new ResourceClosedException("Account not found " + invoiceRequest.getAccountId()));

        CreditCard requestedCreditCard = creditCardService.findCreditCardById(invoiceRequest.getCreditCardId())
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found " + invoiceRequest.getCreditCardId()));

        Invoice updatedInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found " + id));

        updatedInvoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        updatedInvoice.setQuantity(invoiceRequest.getQuantity());
        updatedInvoice.setPay(invoiceRequest.isPay());
        updatedInvoice.setTotalPayment(invoiceRequest.getTotalPayment());
        updatedInvoice.getCreditCard().setId(invoiceRequest.getCreditCardId());
        updatedInvoice.getAccount().setId(invoiceRequest.getAccountId());

        return ResponseEntity.ok(invoiceMapper.toDto(invoiceService.save(updatedInvoice)));
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> create(@RequestBody Invoice invoice){
        Invoice createdInvoice = invoiceService.save(
                new Invoice(
                        null,
                        invoice.getInvoiceDate(),
                        invoice.getQuantity(),
                        invoice.isPay(),
                        invoice.getTotalPayment(),
                        invoice.getCreditCard(),
                        invoice.getAccount()
                )
        );

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdInvoice.getId()))
                .body(invoiceMapper.toDto(createdInvoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        Invoice deletedInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found " + id));
        invoiceService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
