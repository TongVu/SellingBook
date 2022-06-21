package com.axonactive.demo.service;

import com.axonactive.demo.controller.request.InvoiceRequest;
import com.axonactive.demo.entity.Invoice;
import com.axonactive.demo.service.dto.invoiceDto.InvoiceDto;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<Invoice> getAll();

    Invoice save(Invoice invoice);

    void deleteById(Integer id);

    Invoice update(Invoice updatedInvoice, InvoiceRequest invoiceRequest);

    Optional<Invoice> findInvoiceById(Integer id);

    Invoice create(InvoiceRequest invoiceRequest);

    Invoice buyEbook(Integer accountId, Integer ebookId, Integer creditCardId);
}
