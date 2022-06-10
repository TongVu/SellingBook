package com.example.demo.controller.request;

import com.example.demo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {

    private LocalDate dob;

    private String address;

    private Gender gender;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String nationality;
}
