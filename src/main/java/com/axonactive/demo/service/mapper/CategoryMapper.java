package com.axonactive.demo.service.mapper;

import com.axonactive.demo.entity.Category;
import com.axonactive.demo.service.dto.categoryDto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toDto(Category category);

    List<CategoryDto> toDtos(List<Category> category);
}
