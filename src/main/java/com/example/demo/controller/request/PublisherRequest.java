package com.example.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRequest {
    private String name;

    private String phone;

    private String address;

    private String email;
}
