package com.axonactive.demo.service.dto.invoiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private Integer id;

    private LocalDate invoiceDate;

    private Integer quantity;

    private boolean isPay;

    private double totalPayment;

    private Integer creditCardCardNumber;

    private String accountFullName;

}
