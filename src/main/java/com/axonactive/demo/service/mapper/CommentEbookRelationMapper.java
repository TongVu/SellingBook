package com.axonactive.demo.service.mapper;

import com.axonactive.demo.entity.CommentEbookRelation;
import com.axonactive.demo.service.dto.commentEbookRelationDto.CommentEbookRelationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentEbookRelationMapper {
    CommentEbookRelationMapper INSTANCE = Mappers.getMapper(CommentEbookRelationMapper.class);

    @Mapping(source = "comment.commentContent", target = "commentContent")
    @Mapping(source = "comment.date", target = "date")
    @Mapping(target = "accountName",
            expression = "java(commentEbookRelation.getComment().getAccount().getFirstName() + \" \"  + commentEbookRelation.getComment().getAccount().getLastName())")
    @Mapping(source = "ebook.title", target = "ebookTitle")

    CommentEbookRelationDto toDto(CommentEbookRelation commentEbookRelation);

    List<CommentEbookRelationDto> toDtos(List<CommentEbookRelation> commentEbookRelationList);


//    @AfterMapping
//    default void setAccountFullName(CommentEbookRelation commentEbookRelation, @MappingTarget CommentEbookRelationDto target) {
//        target.setAccountName(commentEbookRelation.getComment().getAccount().getFirstName() + " " +commentEbookRelation.getComment().getAccount().getLastName());
//    }

}
