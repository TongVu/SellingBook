package com.example.demo.service.impl;

import com.example.demo.entity.EbookAuthorRelation;
import com.example.demo.repository.EbookAuthorRelationRepository;
import com.example.demo.service.EbookAuthorRelationService;
import com.example.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import com.example.demo.service.mapper.EbookAuthorRelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EbookAuthorRelationServiceImpl implements EbookAuthorRelationService {
    @Autowired
    private EbookAuthorRelationRepository ebookAuthorRelationRepository;
    @Autowired
    private EbookAuthorRelationMapper ebookAuthorRelationMapper;

    @Override
    public List<EbookAuthorRelation> getAll() {
        return ebookAuthorRelationRepository.findAll();
    }

    @Override
    public EbookAuthorRelation save(EbookAuthorRelation ebookAuthorRelation) {
        return ebookAuthorRelationRepository.save(ebookAuthorRelation);
    }

    @Override
    public void deleteById(Integer id) {
        ebookAuthorRelationRepository.deleteById(id);
    }

    @Override
    public Optional<EbookAuthorRelation> findEbookAuthorRelationById(Integer id) {
        return ebookAuthorRelationRepository.findById(id);
    }

    @Override
    public List<EbookAuthorRelation> findByAuthorLastNameContainingIgnoreCase(String authorname){
        return ebookAuthorRelationRepository.findByAuthorLastNameContainingIgnoreCase(authorname);
    }

    @Override
    public List<EbookAuthorRelationDto> findEbooksByPublisher(String publisherName) {
        return ebookAuthorRelationRepository.findEbooksByPublisher(publisherName);
    }
}
