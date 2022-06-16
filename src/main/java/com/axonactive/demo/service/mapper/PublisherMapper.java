package com.axonactive.demo.service.mapper;

import com.axonactive.demo.entity.Publisher;
import com.axonactive.demo.service.dto.publisherDto.PublisherDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherDto toDto(Publisher publisher);
    List<PublisherDto> toDtos(List<Publisher> publisherList);
}
