package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.InvoiceDetailRequest;
import com.axonactive.demo.entity.InvoiceDetail;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.InvoiceDetailService;
import com.axonactive.demo.service.dto.invoiceDetailDto.InvoiceDetailDto;
import com.axonactive.demo.service.mapper.InvoiceDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(InvoiceDetailResource.PATH)
public class InvoiceDetailResource {
    public static final String PATH = "/api/invoicedetails";

    @Autowired
    InvoiceDetailMapper invoiceDetailMapper;

    @Autowired
    InvoiceDetailService invoiceDetailService;

    @GetMapping
    public ResponseEntity<List<InvoiceDetailDto>> getAll() {
        return ResponseEntity.ok(invoiceDetailMapper.toDtos(invoiceDetailService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDetailDto> getById(@PathVariable("id") Integer id) {
        log.info("Searching for invoice detail has id {} ", id);
        InvoiceDetail foundInvoiceDetail = invoiceDetailService.findInvoiceDetailById(id)
                .orElseThrow(BusinessLogicException::invoiceDetailNotFound);

        return ResponseEntity.ok(invoiceDetailMapper.toDto(foundInvoiceDetail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDetailDto> update(@PathVariable("id") Integer id,
                                                   @RequestBody @Valid InvoiceDetailRequest invoiceDetailRequest) {
        log.info("Searching for invoice detail has id {} ", id);
        InvoiceDetail updatedInvoiceDetail = invoiceDetailService.findInvoiceDetailById(id)
                .orElseThrow(BusinessLogicException::invoiceDetailNotFound);

        return ResponseEntity
                .ok(invoiceDetailMapper.toDto(invoiceDetailService.update(updatedInvoiceDetail, invoiceDetailRequest)));
    }

    @PostMapping
    public ResponseEntity<InvoiceDetailDto> create(@RequestBody @Valid InvoiceDetailRequest invoiceDetail) {
        InvoiceDetail createdInvoiceDetail = invoiceDetailService.create(invoiceDetail);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdInvoiceDetail.getId()))
                .body(invoiceDetailMapper.toDto(createdInvoiceDetail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        log.info("Searching for invoice detail has id {} ", id);
        InvoiceDetail deletedInvoiceDetail = invoiceDetailService.findInvoiceDetailById(id)
                .orElseThrow(BusinessLogicException::invoiceDetailNotFound);
        invoiceDetailService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
