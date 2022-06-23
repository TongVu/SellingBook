package com.axonactive.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRequest {

    @NotBlank
    private String name;

    private String phone;

    private String address;

    @Email
    private String email;
}
