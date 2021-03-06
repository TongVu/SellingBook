package com.axonactive.demo.service.mapper;

import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEbookRelationMapper {
    CategoryEbookRelationMapper INSTANCE = Mappers.getMapper(CategoryEbookRelationMapper.class);

    @Mapping(source = "ebook.title", target = "bookTitle")
    @Mapping(source = "ebook.page", target = "bookPage")
    @Mapping(source = "ebook.rating", target = "rating")
    @Mapping(source = "ebook.introduction", target = "introduction")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "ebook.publisher.name", target = "publisherName")

    CategoryEbookRelationDto toDto(CategoryEbookRelation categoryEbookRelation);

    List<CategoryEbookRelationDto> toDtos(List<CategoryEbookRelation> categoryEbookRelationList);

}
