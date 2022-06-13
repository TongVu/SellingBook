package com.example.demo.controller;

import com.example.demo.controller.request.EbookRequest;
import com.example.demo.entity.Ebook;
import com.example.demo.entity.Publisher;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.EbookService;
import com.example.demo.service.PublisherService;
import com.example.demo.service.dto.ebookDto.EbookDto;
import com.example.demo.service.mapper.EbookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(EbookResource.PATH)
public class EbookResource {
    public static final String PATH = "api/ebooks";

    @Autowired
    EbookMapper ebookMapper;

    @Autowired
    EbookService ebookService;

    @Autowired
    PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<EbookDto>> getAll(){
        return ResponseEntity.ok(ebookMapper.toDtos(ebookService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EbookDto> getById(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        Ebook foundEbook = ebookService.findEbookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ebook not found" + id));

        return ResponseEntity.ok(ebookMapper.toDto(foundEbook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EbookDto> update(@PathVariable("id") Integer id,
                                           @RequestBody EbookRequest ebookRequest) throws ResourceNotFoundException{
        Publisher requestedPublisher = publisherService.findPublisherById(ebookRequest.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found " + ebookRequest.getPublisherId()));

        Ebook updatedEbook = ebookService.findEbookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ebook not found " + id));

        updatedEbook.setPage(ebookRequest.getPage());
        updatedEbook.setRating(ebookRequest.getRating());
        updatedEbook.setIntroduction(ebookRequest.getIntroduction());
        updatedEbook.setPurchased(ebookRequest.getPurchased());
        updatedEbook.setViewLinkStatus(ebookRequest.getViewLinkStatus());
        updatedEbook.setPublisher(requestedPublisher);

        return ResponseEntity.ok(ebookMapper.toDto(ebookService.save(updatedEbook)));
    }

    @PostMapping
    public ResponseEntity<EbookDto> create(@RequestBody EbookRequest ebook) throws ResourceNotFoundException{
        Publisher requestedPublisher = publisherService.findPublisherById(ebook.getPublisherId())
                .orElseThrow(() -> new ResourceAccessException("Not found publisher " + ebook.getPublisherId()));

        Ebook createdEbook = ebookService.save(
                new Ebook( null,
                        ebook.getPage(),
                        ebook.getTitle(),
                        ebook.getRating(),
                        ebook.getIntroduction(),
                        ebook.getPurchased(),
                        ebook.getViewLinkStatus(),
                        requestedPublisher
                        )
        );

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdEbook.getId()))
                .body(ebookMapper.toDto(createdEbook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        Ebook deletedEbook = ebookService.findEbookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ebook not found " + id));

        ebookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
