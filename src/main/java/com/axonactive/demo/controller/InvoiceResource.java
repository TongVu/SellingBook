package com.axonactive.demo.controller;

import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.mapper.InvoiceMapper;
import com.axonactive.demo.controller.request.InvoiceRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.entity.CreditCard;
import com.axonactive.demo.entity.Invoice;
import com.axonactive.demo.service.CreditCardService;
import com.axonactive.demo.service.InvoiceService;
import com.axonactive.demo.service.dto.invoiceDto.InvoiceDto;
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
    public ResponseEntity<List<InvoiceDto>> getAll() {
        return ResponseEntity.ok(invoiceMapper.toDtos(invoiceService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getById(@PathVariable("id") Integer id) {
        Invoice foundInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(BusinessLogicException::invoiceNotFound);

        return ResponseEntity.ok(invoiceMapper.toDto(foundInvoice));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDto> update(@PathVariable("id") Integer id,
                                             @RequestBody InvoiceRequest invoiceRequest) {
        Account requestedAccount = accountService.findAccountById(invoiceRequest.getAccountId())
                .orElseThrow(BusinessLogicException::accountNotFound);

        CreditCard requestedCreditCard = creditCardService.findCreditCardById(invoiceRequest.getCreditCardId())
                .orElseThrow(BusinessLogicException::creditCardNotFound);

        Invoice updatedInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(BusinessLogicException::invoiceNotFound);

        updatedInvoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        updatedInvoice.setQuantity(invoiceRequest.getQuantity());
        updatedInvoice.setPay(invoiceRequest.isPay());
        updatedInvoice.setTotalPayment(invoiceRequest.getTotalPayment());
        updatedInvoice.setCreditCard(requestedCreditCard);
        updatedInvoice.setAccount(requestedAccount);

        return ResponseEntity.ok(invoiceMapper.toDto(invoiceService.save(updatedInvoice)));
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> create(@RequestBody InvoiceRequest invoice) {
        Invoice createdInvoice = invoiceService.create(invoice);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdInvoice.getId()))
                .body(invoiceMapper.toDto(createdInvoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Invoice deletedInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(BusinessLogicException::invoiceNotFound);
        invoiceService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
