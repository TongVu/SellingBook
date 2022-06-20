package com.axonactive.demo.service.mapper;

import com.axonactive.demo.entity.Author;
import com.axonactive.demo.service.dto.authorDto.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);


    AuthorDto toDto(Author author);

    List<AuthorDto> toDtos(List<Author> authors);
}
