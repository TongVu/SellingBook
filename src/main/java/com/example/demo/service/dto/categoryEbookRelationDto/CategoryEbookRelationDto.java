package com.example.demo.service.dto.categoryEbookRelationDto;

import com.example.demo.entity.Category;
import com.example.demo.entity.Ebook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEbookRelationDto {

    private Integer id;

    private Ebook ebook;

    private Category category;
}
