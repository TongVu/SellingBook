package com.example.demo.controller.request;

import com.example.demo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private String firstName;

    private String lastName;

    private LocalDate dob;

    private Gender gender;

    private String email;

    private String phone;

    private String address;
}
