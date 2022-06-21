package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.InvoiceDetailRequest;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.entity.Invoice;
import com.axonactive.demo.entity.InvoiceDetail;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.repository.InvoiceDetailRepository;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.service.InvoiceDetailService;
import com.axonactive.demo.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private EbookService ebookService;

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public List<InvoiceDetail> getAll() {
        return invoiceDetailRepository.findAll();
    }

    @Override
    public InvoiceDetail save(InvoiceDetail invoiceDetail) {
        return invoiceDetailRepository.save(invoiceDetail);
    }

    @Override
    public InvoiceDetail update(InvoiceDetail invoiceDetail, InvoiceDetailRequest invoiceDetailRequest) {
        log.info("Searching for invoice has id {} ", invoiceDetailRequest.getInvoiceId());
        Invoice requestedInvoice = invoiceService.findInvoiceById(invoiceDetailRequest.getInvoiceId())
                .orElseThrow(BusinessLogicException::invoiceNotFound);

        log.info("Searching for ebook has id {} ", invoiceDetailRequest.getEbookId());
        Ebook requestedEbook = ebookService.findEbookById(invoiceDetailRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        invoiceDetail.setDateAdded(invoiceDetailRequest.getDateAdded());
        invoiceDetail.setEbookPrice(invoiceDetailRequest.getEbookPrice());
        invoiceDetail.setInvoice(requestedInvoice);
        invoiceDetail.setEbook(requestedEbook);

        return invoiceDetailRepository.save(invoiceDetail);
    }

    @Override
    public InvoiceDetail create(InvoiceDetailRequest invoiceDetailRequest) {
        log.info("Searching for invoice has id {} ", invoiceDetailRequest.getInvoiceId());
        Invoice requestedInvoice = invoiceService.findInvoiceById(invoiceDetailRequest.getInvoiceId())
                .orElseThrow(BusinessLogicException::invoiceNotFound);

        log.info("Searching for ebook has id {} ", invoiceDetailRequest.getEbookId());
        Ebook requestedEbook = ebookService.findEbookById(invoiceDetailRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        InvoiceDetail createdInvoiceDetail = new InvoiceDetail();
        createdInvoiceDetail.setDateAdded(invoiceDetailRequest.getDateAdded());
        createdInvoiceDetail.setEbookPrice(invoiceDetailRequest.getEbookPrice());
        createdInvoiceDetail.setInvoice(requestedInvoice);
        createdInvoiceDetail.setEbook(requestedEbook);

        return invoiceDetailRepository.save(createdInvoiceDetail);
    }

    @Override
    public void deleteById(Integer id) {
        invoiceDetailRepository.deleteById(id);
    }

    @Override
    public Optional<InvoiceDetail> findInvoiceDetailById(Integer id) {
        return invoiceDetailRepository.findById(id);
    }

    @Override
    public Optional<InvoiceDetail> findByEbookId(Integer ebookId) {
        return invoiceDetailRepository.findByEbookId(ebookId);
    }
}
