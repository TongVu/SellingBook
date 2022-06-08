package com.example.demo.service;

import com.example.demo.entity.EbookAuthorRelation;

import java.util.List;
import java.util.Optional;

public interface EbookAuthorRelationService {

    List<EbookAuthorRelation> getAll();

    void save(EbookAuthorRelation ebookAuthorRelation);

    void deleteById(Integer id);

    Optional<EbookAuthorRelation> findEbookAuthorRelationById(Integer id);
}
