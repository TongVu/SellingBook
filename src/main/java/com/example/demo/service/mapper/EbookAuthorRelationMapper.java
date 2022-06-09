package com.example.demo.service.mapper;

import com.example.demo.entity.EbookAuthorRelation;
import com.example.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EbookAuthorRelationMapper {
    EbookAuthorRelationMapper INSTANCE = Mappers.getMapper(EbookAuthorRelationMapper.class);

    @Mapping(source = "ebook.title", target = "ebookTitle")
    @Mapping(source = "ebook.page", target = "ebookPage")
    @Mapping(source = "ebook.introduction", target = "ebookIntroduction")
    @Mapping(source = "ebook.publisher.name", target = "ebookPublisherName")
    @Mapping(target = "authorFullName", expression = "java(ebookAuthorRelation.getAuthor().getFirstName() + \" \" + ebookAuthorRelation.getAuthor().getLastName())")
    @Mapping(source = "author.email", target = "authorEmail")
    @Mapping(source = "author.gender", target = "authorGender")


    EbookAuthorRelationDto toDto(EbookAuthorRelation ebookAuthorRelation);
    List<EbookAuthorRelationDto> toDtos(List<EbookAuthorRelation> ebookAuthorRelations);

//    @AfterMapping
//    default void setAuthorFullName(EbookAuthorRelation ebookAuthorRelation, @MappingTarget EbookAuthorRelationDto target) {
//        target.setAuthorFullName(ebookAuthorRelation.getAuthor().getFirstName()+ " " +ebookAuthorRelation.getAuthor().getLastName());
//    }
}
