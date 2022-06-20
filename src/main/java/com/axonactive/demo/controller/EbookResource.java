package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.EbookRequest;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.service.dto.ebookDto.EbookDto;
import com.axonactive.demo.service.dto.ebookDto.EbookInfoCategoryAuthorDto;
import com.axonactive.demo.service.mapper.EbookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(EbookResource.PATH)
public class EbookResource {
    public static final String PATH = "api/ebooks";

    @Autowired
    EbookMapper ebookMapper;

    @Autowired
    EbookService ebookService;

    @GetMapping
    public ResponseEntity<List<EbookDto>> getAll() {
        return ResponseEntity.ok(ebookMapper.toDtos(ebookService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EbookDto> getById(@PathVariable("id") Integer id) {
        log.info("Searching for ebook has id {} ", id);
        Ebook foundEbook = ebookService.findEbookById(id)
                .orElseThrow(BusinessLogicException::ebookNotFound);

        return ResponseEntity.ok(ebookMapper.toDto(foundEbook));
    }

    @GetMapping("/find")
    public ResponseEntity<List<EbookInfoCategoryAuthorDto>> getEbookByPage(@Param("pages") Integer pages) {
        return ResponseEntity.ok(ebookService.findEbookByPagesGreaterThan(pages));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EbookDto> update(@PathVariable("id") Integer id,
                                           @RequestBody EbookRequest ebookRequest) {
        log.info("Searching for ebook has id {} ", id);
        Ebook updatedEbook = ebookService.findEbookById(id)
                .orElseThrow(BusinessLogicException::ebookAuthorRelationNotFound);

        return ResponseEntity.ok(ebookMapper.toDto(ebookService.update(updatedEbook, ebookRequest)));
    }

    @PostMapping
    public ResponseEntity<EbookDto> create(@RequestBody EbookRequest ebook) {
        Ebook createdEbook = ebookService.create(ebook);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdEbook.getId()))
                .body(ebookMapper.toDto(createdEbook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        log.info("Searching for ebook has id {} ", id);
        Ebook deletedEbook = ebookService.findEbookById(id)
                .orElseThrow(BusinessLogicException::ebookNotFound);

        ebookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
