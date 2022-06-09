package com.example.demo.service.mapper;

import com.example.demo.entity.Ebook;
import com.example.demo.service.dto.ebookDto.EbookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EbookMapper {
    EbookMapper INSTANCE = Mappers.getMapper(EbookMapper.class);

    @Mapping(source = "publisher.name", target = "publisherName")
    @Mapping(source = "publisher.email", target = "publisherEmail")

    EbookDto toDto(Ebook ebook);
    List<EbookDto> toDtos(List<Ebook> ebooks);
}
