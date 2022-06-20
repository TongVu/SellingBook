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

    //    private Invoice invoice;
    private LocalDate invoiceInvoiceDate;

    private Integer invoiceQuantity;

    private double invoiceTotalPayment;

    // private invoiceCreditCard
    private Integer invoiceCreditCardCardNumber;

    //private invoiceAccount
    private String invoiceAccountFullName;

    //    private Ebook ebook;
    private String ebookTitle;
}
