package com.example.demo.controller;

import com.example.demo.controller.request.EbookAuthorRelationRequest;
import com.example.demo.entity.Author;
import com.example.demo.entity.Ebook;
import com.example.demo.entity.EbookAuthorRelation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.AuthorService;
import com.example.demo.service.EbookAuthorRelationService;
import com.example.demo.service.EbookService;
import com.example.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import com.example.demo.service.mapper.EbookAuthorRelationMapper;
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
    public ResponseEntity<List<EbookAuthorRelationDto>> getAll(){
        return ResponseEntity.ok(ebookAuthorRelationMapper.toDtos(ebookAuthorRelationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EbookAuthorRelationDto> getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        EbookAuthorRelation ebookAuthorRelation = ebookAuthorRelationService.findEbookAuthorRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        return ResponseEntity.ok(ebookAuthorRelationMapper.toDto(ebookAuthorRelation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EbookAuthorRelationDto> update(@PathVariable("id") Integer id,
                                                         @RequestBody EbookAuthorRelationRequest ebookAuthorRelationRequest) throws ResourceNotFoundException{
        Ebook requestedEbook = ebookService.findEbookById(ebookAuthorRelationRequest.getEbookId())
                .orElseThrow(() -> new ResourceNotFoundException("Ebook not found " + ebookAuthorRelationRequest.getEbookId()));

        Author requestedAuthor = authorService.findAuthorById(ebookAuthorRelationRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found " + ebookAuthorRelationRequest.getAuthorId()));

        EbookAuthorRelation updatedEbookAuthorRelation = ebookAuthorRelationService.findEbookAuthorRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        updatedEbookAuthorRelation.setEbook(requestedEbook);
        updatedEbookAuthorRelation.setAuthor(requestedAuthor);
        ebookAuthorRelationService.save(updatedEbookAuthorRelation);

        return ResponseEntity.ok(ebookAuthorRelationMapper.toDto(updatedEbookAuthorRelation));
    }

    // check this
    @PostMapping
    public ResponseEntity<EbookAuthorRelationDto> create(@RequestBody EbookAuthorRelation ebookAuthorRelationRequest){
        EbookAuthorRelation ebookAuthorRelation = new EbookAuthorRelation(
                null,
                ebookAuthorRelationRequest.getEbook(),
                ebookAuthorRelationRequest.getAuthor()
        );

        return ResponseEntity
                .created(URI.create(PATH + "/" + ebookAuthorRelation.getId()))
                .body(ebookAuthorRelationMapper.toDto(ebookAuthorRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        EbookAuthorRelation deletedEbookAuthorRelation =  ebookAuthorRelationService.findEbookAuthorRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
        ebookAuthorRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}