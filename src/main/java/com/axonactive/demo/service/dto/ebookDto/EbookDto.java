package com.axonactive.demo.service.dto.ebookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookDto {

    private String title;

    private Integer page;

    private Double rating;

    private String introduction;

    private Boolean purchased;

    private String viewLinkStatus;

    private String publisherName;

    private String publisherEmail;
}
