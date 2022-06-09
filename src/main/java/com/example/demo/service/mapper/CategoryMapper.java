package com.example.demo.service.mapper;

import com.example.demo.entity.Category;
import com.example.demo.service.dto.categoryDto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toDto(Category category);
    List<CategoryDto> toDtos(List<Category> category);
}
