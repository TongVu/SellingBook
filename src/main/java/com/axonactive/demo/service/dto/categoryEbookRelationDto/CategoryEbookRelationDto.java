package com.axonactive.demo.service.dto.categoryEbookRelationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEbookRelationDto {

    private String bookTitle;

    private Integer bookPage;

    private Double rating;

    private String introduction;

    private String categoryName;

    private String publisherName;

}
