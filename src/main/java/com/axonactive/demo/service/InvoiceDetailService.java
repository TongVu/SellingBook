package com.axonactive.demo.service;

import com.axonactive.demo.controller.request.InvoiceDetailRequest;
import com.axonactive.demo.entity.InvoiceDetail;

import java.util.List;
import java.util.Optional;

public interface InvoiceDetailService {
    List<InvoiceDetail> getAll();

    InvoiceDetail save(InvoiceDetail invoiceDetail);

    InvoiceDetail update(InvoiceDetail invoiceDetail, InvoiceDetailRequest invoiceDetailRequest);

    InvoiceDetail create(InvoiceDetailRequest invoiceDetailRequest);

    void deleteById(Integer id);

    Optional<InvoiceDetail> findInvoiceDetailById(Integer id);

    Optional<InvoiceDetail> findByEbookId(Integer ebookId);
}