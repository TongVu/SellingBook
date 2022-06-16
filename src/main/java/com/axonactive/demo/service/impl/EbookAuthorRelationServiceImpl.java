package com.axonactive.demo.service.impl;

import com.axonactive.demo.repository.EbookAuthorRelationRepository;
import com.axonactive.demo.service.EbookAuthorRelationService;
import com.axonactive.demo.service.mapper.EbookAuthorRelationMapper;
import com.axonactive.demo.entity.EbookAuthorRelation;
import com.axonactive.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
