package com.example.demo.controller.request;

import com.example.demo.entity.Ebook;
import com.example.demo.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailRequest {

    private LocalDate dateAdded;

    private double ebookPrice;

    private Integer invoiceId;

    private Integer  ebookId;
}
