package com.axonactive.demo.repository;

import com.axonactive.demo.controller.request.InvoiceRequest;
import com.axonactive.demo.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
