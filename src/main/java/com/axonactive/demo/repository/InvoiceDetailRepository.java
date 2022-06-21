package com.axonactive.demo.repository;

import com.axonactive.demo.entity.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {
    Optional<InvoiceDetail> findByEbookId(Integer ebookId);
}
