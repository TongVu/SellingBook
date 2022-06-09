package com.example.demo.service;

import com.example.demo.entity.InvoiceDetail;

import java.util.List;
import java.util.Optional;

public interface InvoiceDetailService {
    List<InvoiceDetail> getAll();

    InvoiceDetail save(InvoiceDetail invoiceDetail);

    void deleteById(Integer id);

    Optional<InvoiceDetail> findInvoiceDetailById(Integer id);
}
