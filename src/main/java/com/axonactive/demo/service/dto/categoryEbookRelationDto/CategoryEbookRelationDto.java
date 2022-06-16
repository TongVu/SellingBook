package com.axonactive.demo.service.dto.categoryEbookRelationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEbookRelationDto {

//    private Ebook ebook
    private String bookTitle;

    private Integer bookPage;

    private Integer rating;

    private String introduction;

    private String categoryName;

    private String publisherName;

}
