package com.axonactive.demo.controller;

import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.mapper.InvoiceMapper;
import com.axonactive.demo.controller.request.InvoiceRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.entity.CreditCard;
import com.axonactive.demo.entity.Invoice;
import com.axonactive.demo.service.CreditCardService;
import com.axonactive.demo.service.InvoiceService;
import com.axonactive.demo.service.dto.invoiceDto.InvoiceDto;
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
    public ResponseEntity<List<InvoiceDto>> getAll() {
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
        Invoice updatedInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found " + id));

        return ResponseEntity.ok(invoiceMapper.toDto(invoiceService.update(updatedInvoice, invoiceRequest)));
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> create(@RequestBody InvoiceRequest invoice) throws ResourceNotFoundException {
        Invoice createdInvoice = invoiceService.create(invoice);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdInvoice.getId()))
                .body(invoiceMapper.toDto(createdInvoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Invoice deletedInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found " + id));
        invoiceService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
