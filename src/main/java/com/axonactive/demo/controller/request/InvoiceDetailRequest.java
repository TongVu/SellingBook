package com.axonactive.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailRequest {

    private LocalDate dateAdded;

    @Min(0)
    private double ebookPrice;

    private Integer invoiceId;

    private Integer ebookId;
}
