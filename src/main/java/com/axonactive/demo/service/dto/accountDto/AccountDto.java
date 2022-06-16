package com.axonactive.demo.service.dto.accountDto;

import com.axonactive.demo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccountDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
    private String email;
    private String phone;
    private String address;
}
