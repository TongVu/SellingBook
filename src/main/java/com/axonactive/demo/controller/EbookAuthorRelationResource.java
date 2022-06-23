package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.EbookAuthorRelationRequest;
import com.axonactive.demo.entity.EbookAuthorRelation;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.EbookAuthorRelationService;
import com.axonactive.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import com.axonactive.demo.service.mapper.EbookAuthorRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(EbookAuthorRelationResource.PATH)
public class EbookAuthorRelationResource {
    public static final String PATH = "/api/ebookauthorrelations";

    @Autowired
    EbookAuthorRelationMapper ebookAuthorRelationMapper;

    @Autowired
    EbookAuthorRelationService ebookAuthorRelationService;

    @GetMapping
    public ResponseEntity<List<EbookAuthorRelationDto>> getAll() {
        return ResponseEntity.ok(ebookAuthorRelationMapper.toDtos(ebookAuthorRelationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EbookAuthorRelationDto> getById(@PathVariable("id") Integer id) {
        log.info("Searching for ebook_author_relation has id {} ", id);
        EbookAuthorRelation ebookAuthorRelation = ebookAuthorRelationService.findEbookAuthorRelationById(id)
                .orElseThrow(BusinessLogicException::ebookAuthorRelationNotFound);

        return ResponseEntity.ok(ebookAuthorRelationMapper.toDto(ebookAuthorRelation));
    }

    @GetMapping("/find")
    public ResponseEntity<List<EbookAuthorRelationDto>> getAuthorsByLastNameContaining(
            @RequestParam(value = "authorname", defaultValue = "empty", required = false) @NotBlank String authorName,
            @RequestParam(value = "publishername", defaultValue = "empty", required = false) @NotBlank String publisherName) {
        if (!publisherName.equals("empty")) {
            List<EbookAuthorRelationDto> results = ebookAuthorRelationService.findEbooksByPublisher(publisherName);

            return ResponseEntity.ok(results);
        }

        if (!authorName.equals("empty")) {
            List<EbookAuthorRelation> requestedAuthors = ebookAuthorRelationService.findByAuthorLastNameContainingIgnoreCase(authorName);

            return ResponseEntity.ok(ebookAuthorRelationMapper.toDtos(requestedAuthors));
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EbookAuthorRelationDto> update(@PathVariable("id") Integer id,
                                                         @RequestBody EbookAuthorRelationRequest ebookAuthorRelationRequest) {
        log.info("Searching for ebook_author_relation has id {} ", id);
        EbookAuthorRelation updatedEbookAuthorRelation = ebookAuthorRelationService.findEbookAuthorRelationById(id)
                .orElseThrow(BusinessLogicException::ebookAuthorRelationNotFound);

        return ResponseEntity.ok(ebookAuthorRelationMapper.toDto(ebookAuthorRelationService.update(updatedEbookAuthorRelation, ebookAuthorRelationRequest)));
    }

    @PostMapping
    public ResponseEntity<EbookAuthorRelationDto> create(@RequestBody EbookAuthorRelationRequest ebookAuthorRelationRequest) {
        EbookAuthorRelation createdEbookAuthorRelation = ebookAuthorRelationService.create(ebookAuthorRelationRequest);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdEbookAuthorRelation.getId()))
                .body(ebookAuthorRelationMapper.toDto(createdEbookAuthorRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        log.info("Searching for ebook_author_relation has id {} ", id);
        EbookAuthorRelation deletedEbookAuthorRelation = ebookAuthorRelationService.findEbookAuthorRelationById(id)
                .orElseThrow(BusinessLogicException::ebookAuthorRelationNotFound);
        ebookAuthorRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
