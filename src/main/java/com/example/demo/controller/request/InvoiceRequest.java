package com.example.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    private LocalDate invoiceDate;

    private Integer quantity;

    private boolean isPay;

    private double totalPayment;

    private Integer creditCardId;

    private Integer accountId;
}
