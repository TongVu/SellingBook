package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.EbookAuthorRelationRequest;
import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.service.AuthorService;
import com.axonactive.demo.service.EbookAuthorRelationService;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import com.axonactive.demo.service.mapper.EbookAuthorRelationMapper;
import com.axonactive.demo.entity.Author;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.entity.EbookAuthorRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(EbookAuthorRelationResource.PATH)
public class EbookAuthorRelationResource {
    public static final String PATH = "/api/ebookauthorrelations";

    @Autowired
    EbookAuthorRelationMapper ebookAuthorRelationMapper;
    @Autowired
    EbookAuthorRelationService ebookAuthorRelationService;
    @Autowired
    EbookService ebookService;
    @Autowired
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<EbookAuthorRelationDto>> getAll() {
        return ResponseEntity.ok(ebookAuthorRelationMapper.toDtos(ebookAuthorRelationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EbookAuthorRelationDto> getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        EbookAuthorRelation ebookAuthorRelation = ebookAuthorRelationService.findEbookAuthorRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        return ResponseEntity.ok(ebookAuthorRelationMapper.toDto(ebookAuthorRelation));
    }

    @GetMapping("/find")
    public ResponseEntity<List<EbookAuthorRelationDto>> getAuthorsByLastNameContaining(
            @RequestParam(value = "authorname", defaultValue = "empty", required = false) String authorName,
            @RequestParam(value = "publishername", defaultValue = "empty", required = false) String publisherName) {
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
                                                         @RequestBody EbookAuthorRelationRequest ebookAuthorRelationRequest) throws ResourceNotFoundException {
        EbookAuthorRelation updatedEbookAuthorRelation = ebookAuthorRelationService.findEbookAuthorRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        return ResponseEntity.ok(ebookAuthorRelationMapper.toDto(ebookAuthorRelationService.update(updatedEbookAuthorRelation, ebookAuthorRelationRequest)));
    }

    @PostMapping
    public ResponseEntity<EbookAuthorRelationDto> create(@RequestBody EbookAuthorRelationRequest ebookAuthorRelationRequest) throws ResourceNotFoundException {
        EbookAuthorRelation createdEbookAuthorRelation = ebookAuthorRelationService.create(ebookAuthorRelationRequest);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdEbookAuthorRelation.getId()))
                .body(ebookAuthorRelationMapper.toDto(createdEbookAuthorRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        EbookAuthorRelation deletedEbookAuthorRelation = ebookAuthorRelationService.findEbookAuthorRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
        ebookAuthorRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
