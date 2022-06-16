package com.axonactive.demo.service.impl;

import com.axonactive.demo.repository.InvoiceDetailRepository;
import com.axonactive.demo.service.InvoiceDetailService;
import com.axonactive.demo.entity.InvoiceDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
    @Autowired
    private final InvoiceDetailRepository invoiceDetailRepository;

    @Override
    public List<InvoiceDetail> getAll() {
        return invoiceDetailRepository.findAll();
    }

    @Override
    public InvoiceDetail save(InvoiceDetail invoiceDetail) {
        return invoiceDetailRepository.save(invoiceDetail);
    }

    @Override
    public void deleteById(Integer id) {
        invoiceDetailRepository.deleteById(id);
    }

    @Override
    public Optional<InvoiceDetail> findInvoiceDetailById(Integer id) {
        return invoiceDetailRepository.findById(id);
    }
}
