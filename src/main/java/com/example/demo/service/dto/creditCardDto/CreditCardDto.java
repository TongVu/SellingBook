package com.example.demo.service.dto.creditCardDto;

import com.example.demo.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDto {
    private Integer id;

    private Integer cardNumber;

    private LocalDate expiredDate;

    private Double balance;

    private Account account;
}
