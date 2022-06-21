package com.axonactive.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEbookRelationRequest {

    private Integer ebookId;

    private Integer categoryId;

}
