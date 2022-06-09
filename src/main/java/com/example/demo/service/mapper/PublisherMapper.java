package com.example.demo.service.mapper;

import com.example.demo.entity.Publisher;
import com.example.demo.service.dto.publisherDto.PublisherDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherDto toDto(Publisher publisher);
    List<PublisherDto> toDtos(List<Publisher> publisherList);
}
