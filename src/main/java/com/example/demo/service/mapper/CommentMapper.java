package com.example.demo.service.mapper;

import com.example.demo.entity.Comment;
import com.example.demo.service.dto.commentDto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "accountFullName", expression = "java(comment.getAccount().getFirstName() + \" \" + comment.getAccount().getLastName())")

    CommentDto toDto(Comment comment);
    List<CommentDto> toDtos(List<Comment> comments);
}
