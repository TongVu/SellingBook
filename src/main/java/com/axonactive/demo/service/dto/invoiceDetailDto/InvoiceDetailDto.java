package com.axonactive.demo.service.dto.invoiceDetailDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailDto {

    private LocalDate dateAdded;

    private double ebookPrice;

    private LocalDate invoiceInvoiceDate;

    private Integer invoiceQuantity;

    private double invoiceTotalPayment;

    private Integer invoiceCreditCardCardNumber;

    private String invoiceAccountFullName;

    private String ebookTitle;
}
