package com.example.demo.service.dto.creditCardDto;

import com.example.demo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDto {

    private Integer cardNumber;

    private LocalDate expiredDate;

    private Double balance;

    private String accountFullName;

    private LocalDate accountDob;

    private Gender accountGender;

    private String accountEmail;

    private String accountPhone;
}
