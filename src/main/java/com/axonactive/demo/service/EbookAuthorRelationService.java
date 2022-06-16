package com.axonactive.demo.service;

import com.axonactive.demo.entity.EbookAuthorRelation;
import com.axonactive.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;

import java.util.List;
import java.util.Optional;

public interface EbookAuthorRelationService {
    List<EbookAuthorRelation> getAll();

    EbookAuthorRelation save(EbookAuthorRelation ebookAuthorRelation);

    void deleteById(Integer id);

    Optional<EbookAuthorRelation> findEbookAuthorRelationById(Integer id);

    List<EbookAuthorRelation> findByAuthorLastNameContainingIgnoreCase(String authorname);

    List<EbookAuthorRelationDto> findEbooksByPublisher(String publisherName);
}
