package com.axonactive.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    private LocalDate invoiceDate;

    @Min(0)
    private Integer quantity;

    private boolean isPay;

    @Min(0)
    private double totalPayment;

    private Integer creditCardId;

    private Integer accountId;
}
