package com.example.demo.service.dto.categoryDto;

import com.example.demo.entity.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer id;

    private CategoryName name;

    private Integer numberOfBooks;
}
