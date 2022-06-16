package com.axonactive.demo.service.dto.publisherDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {
    private String name;

    private String phone;

    private String address;

    private String email;
}
