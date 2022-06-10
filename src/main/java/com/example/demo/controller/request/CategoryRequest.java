package com.example.demo.controller.request;

import com.example.demo.entity.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private CategoryName name;

    private Integer numberOfBooks;
}
