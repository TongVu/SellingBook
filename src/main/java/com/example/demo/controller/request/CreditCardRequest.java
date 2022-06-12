package com.example.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequest {

    private Integer cardNumber;

    private LocalDate expiredDate;

    private Double balance;

    private Integer accountId;

}
