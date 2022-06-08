package com.example.demo.service.dto.invoiceDto;

import com.example.demo.entity.Account;
import com.example.demo.entity.Invoice;
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

    private Invoice invoice;

    private Account account;

}
