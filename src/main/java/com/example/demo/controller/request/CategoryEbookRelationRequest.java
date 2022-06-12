package com.example.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEbookRelationRequest {

//    private String bookTitle;
//
//    private Integer bookPage;
//
//    private Integer rating;
//
//    private String publisherName;
//
//    private String introduction;

    private Integer ebookId;

    private Integer categoryId;
//    private String categoryName;

}
