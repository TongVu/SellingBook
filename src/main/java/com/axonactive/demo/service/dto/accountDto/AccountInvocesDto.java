package com.axonactive.demo.service.dto.accountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInvocesDto {

    private LocalDate invoiceDate;

    private Integer quantity;

    private Boolean isPay;

    private Double totalPayment;

    private LocalDate dateAdded;

    private Double ebookPrice;

}
