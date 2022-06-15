package com.example.demo.service;

import com.example.demo.entity.EbookAuthorRelation;
import com.example.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EbookAuthorRelationService {
    List<EbookAuthorRelation> getAll();

    EbookAuthorRelation save(EbookAuthorRelation ebookAuthorRelation);

    void deleteById(Integer id);

    Optional<EbookAuthorRelation> findEbookAuthorRelationById(Integer id);

    List<EbookAuthorRelation> findByAuthorLastNameContainingIgnoreCase(String authorname);
}
