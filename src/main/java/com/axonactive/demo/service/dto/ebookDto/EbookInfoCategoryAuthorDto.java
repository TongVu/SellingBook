package com.axonactive.demo.service.dto.ebookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookInfoCategoryAuthorDto {

    private String title;

    private Integer page;

    private Double rating;

    private String introduction;

    private Boolean purchased;

    private String publisherName;

    private String publisherEmail;

    private String categoryName;

    private String authorFullName;
}
