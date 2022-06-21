package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.InvoiceRequest;
import com.axonactive.demo.entity.Invoice;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.InvoiceService;
import com.axonactive.demo.service.dto.invoiceDto.InvoiceDto;
import com.axonactive.demo.service.mapper.InvoiceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(InvoiceResource.PATH)
public class InvoiceResource {
    public static final String PATH = "/api/invoices";

    @Autowired
    InvoiceMapper invoiceMapper;

    @Autowired
    InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAll() {
        return ResponseEntity.ok(invoiceMapper.toDtos(invoiceService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getById(@PathVariable("id") Integer id) {
        log.info("Searching for invoice has id {} ", id);
        Invoice foundInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(BusinessLogicException::invoiceNotFound);

        return ResponseEntity.ok(invoiceMapper.toDto(foundInvoice));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDto> update(@PathVariable("id") Integer id,
                                             @RequestBody InvoiceRequest invoiceRequest) {
        log.info("Searching for invoice has id {} ", id);
        Invoice updatedInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(BusinessLogicException::invoiceNotFound);

        return ResponseEntity.ok(invoiceMapper.toDto(invoiceService.update(updatedInvoice, invoiceRequest)));
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> create(@RequestBody InvoiceRequest invoice) {
        Invoice createdInvoice = invoiceService.create(invoice);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdInvoice.getId()))
                .body(invoiceMapper.toDto(createdInvoice));
    }

    @PostMapping("/buy")
    public ResponseEntity<InvoiceDto> buyEbook(@RequestParam("accountId") Integer accountId,
                                               @RequestParam("ebookId") Integer ebookId,
                                               @RequestParam("creditCardId") Integer creditCardId) {
        Invoice createdInvoice = invoiceService.buyEbook(accountId, ebookId, creditCardId);

        invoiceService.findInvoiceById(createdInvoice.getId())
                .orElseThrow(BusinessLogicException::accountAndCreditCardNotMatch);

        return ResponseEntity.ok(invoiceMapper.toDto(createdInvoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        log.info("Searching for invoice has id {} ", id);
        Invoice deletedInvoice = invoiceService.findInvoiceById(id)
                .orElseThrow(BusinessLogicException::invoiceNotFound);
        invoiceService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
