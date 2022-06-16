package com.axonactive.demo.service.mapper;

import com.axonactive.demo.entity.Comment;
import com.axonactive.demo.service.dto.commentDto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "accountFullName", expression = "java(comment.getAccount().getFirstName() + \" \" + comment.getAccount().getLastName())")

    CommentDto toDto(Comment comment);
    List<CommentDto> toDtos(List<Comment> comments);
}
