package com.example.demo.service.impl;

import com.example.demo.entity.EbookAuthorRelation;
import com.example.demo.repository.EbookAuthorRelationRepository;
import com.example.demo.service.EbookAuthorRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EbookAuthorRelationServiceImpl implements EbookAuthorRelationService {
    @Autowired
    private final EbookAuthorRelationRepository ebookAuthorRelationRepository;

    @Override
    public List<EbookAuthorRelation> getAll() {
        return ebookAuthorRelationRepository.findAll();
    }

    @Override
    public void save(EbookAuthorRelation ebookAuthorRelation) {
        ebookAuthorRelationRepository.save(ebookAuthorRelation);
    }

    @Override
    public void deleteById(Integer id) {
        ebookAuthorRelationRepository.deleteById(id);
    }

    @Override
    public Optional<EbookAuthorRelation> findEbookAuthorRelationById(Integer id) {
        return ebookAuthorRelationRepository.findById(id);
    }
}
