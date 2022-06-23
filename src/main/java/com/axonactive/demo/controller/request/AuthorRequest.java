package com.axonactive.demo.controller.request;

import com.axonactive.demo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {


    private LocalDate dob;

    private String address;

    private Gender gender;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    private String phone;

    private String nationality;
}
