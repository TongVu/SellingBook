package com.example.demo.service.dto.ebookAuthorRelationDto;

import com.example.demo.entity.Author;
import com.example.demo.entity.Ebook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookAuthorRelationDto {

    private Integer id;

    private Ebook ebook;

    private Author author;
}
