package com.example.demo.service;

import com.example.demo.entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<Invoice> getAll();

    void save(Invoice invoice);

    void deleteById(Integer id);

    Optional<Invoice> findInvoiceById(Integer id);
}
