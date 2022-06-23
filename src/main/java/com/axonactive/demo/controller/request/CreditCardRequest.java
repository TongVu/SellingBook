package com.axonactive.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequest {

    private Integer cardNumber;

    private LocalDate expiredDate;

    @Min(0)
    private Double balance;

    private Integer accountId;

}
