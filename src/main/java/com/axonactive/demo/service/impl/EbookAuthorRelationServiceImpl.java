package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.EbookAuthorRelationRequest;
import com.axonactive.demo.entity.Author;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.entity.EbookAuthorRelation;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.repository.EbookAuthorRelationRepository;
import com.axonactive.demo.service.AuthorService;
import com.axonactive.demo.service.EbookAuthorRelationService;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import com.axonactive.demo.service.mapper.EbookAuthorRelationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EbookAuthorRelationServiceImpl implements EbookAuthorRelationService {
    @Autowired
    private EbookAuthorRelationRepository ebookAuthorRelationRepository;

    @Autowired
    private EbookAuthorRelationMapper ebookAuthorRelationMapper;

    @Autowired
    private EbookService ebookService;

    @Autowired
    private AuthorService authorService;

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
    public EbookAuthorRelation update(EbookAuthorRelation updatedEbookAuthorRelation, EbookAuthorRelationRequest ebookAuthorRelationRequest) {
        log.info("Searching for ebook has id {} ", ebookAuthorRelationRequest.getEbookId());
        Ebook requestedEbook = ebookService.findEbookById(ebookAuthorRelationRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        log.info("Searching for author has id {} ", ebookAuthorRelationRequest.getAuthorId());
        Author requestedAuthor = authorService.findAuthorById(ebookAuthorRelationRequest.getAuthorId())
                .orElseThrow(BusinessLogicException::authorNotFound);

        updatedEbookAuthorRelation.setEbook(requestedEbook);
        updatedEbookAuthorRelation.setAuthor(requestedAuthor);

        return ebookAuthorRelationRepository.save(updatedEbookAuthorRelation);
    }

    @Override
    public EbookAuthorRelation create(EbookAuthorRelationRequest ebookAuthorRelationRequest) {
        log.info("Searching for ebook has id {} ", ebookAuthorRelationRequest.getEbookId());
        Ebook requestedEbook = ebookService.findEbookById(ebookAuthorRelationRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        log.info("Searching for author has id {} ", ebookAuthorRelationRequest.getAuthorId());
        Author requestedAuthor = authorService.findAuthorById(ebookAuthorRelationRequest.getAuthorId())
                .orElseThrow(BusinessLogicException::authorNotFound);

        EbookAuthorRelation createdEbookAuthorRelation = new EbookAuthorRelation();
        createdEbookAuthorRelation.setEbook(requestedEbook);
        createdEbookAuthorRelation.setAuthor(requestedAuthor);

        return ebookAuthorRelationRepository.save(createdEbookAuthorRelation);
    }

    @Override
    public List<EbookAuthorRelation> findByAuthorLastNameContainingIgnoreCase(String authorname) {
        return ebookAuthorRelationRepository.findByAuthorLastNameContainingIgnoreCase(authorname);
    }

    @Override
    public List<EbookAuthorRelationDto> findEbooksByPublisher(String publisherName) {
        return ebookAuthorRelationRepository.findEbooksByPublisher(publisherName);
    }
}
