package com.axonactive.demo.service;

import com.axonactive.demo.entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<Invoice> getAll();

    Invoice save(Invoice invoice);

    void deleteById(Integer id);

    Optional<Invoice> findInvoiceById(Integer id);
}
