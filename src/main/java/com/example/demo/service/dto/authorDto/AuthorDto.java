package com.example.demo.service.dto.authorDto;

import com.example.demo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Integer id;

    private LocalDate dob;

    private String address;

    private Gender gender;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String nationality;
}
