package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.PublisherRequest;
import com.axonactive.demo.entity.Publisher;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.PublisherService;
import com.axonactive.demo.service.dto.publisherDto.PublisherDto;
import com.axonactive.demo.service.mapper.PublisherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(PublisherResource.PATH)
public class PublisherResource {
    public static final String PATH = "/api/publishers";

    @Autowired
    PublisherService publisherService;

    @Autowired
    PublisherMapper publisherMapper;

    @GetMapping
    public ResponseEntity<List<PublisherDto>> getAll() {
        return ResponseEntity.ok(publisherMapper.toDtos(publisherService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> getById(@PathVariable("id") Integer id) {
        log.info("Searching for publisher has id {} ", id);
        Publisher publisher = publisherService.findPublisherById(id)
                .orElseThrow(BusinessLogicException::publisherNotFound);

        return ResponseEntity.ok(publisherMapper.toDto(publisher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDto> update(@PathVariable("id") Integer id,
                                               @RequestBody PublisherRequest publisherRequest) {
        log.info("Searching for publisher has id {} ", id);
        Publisher updatedPublisher = publisherService.findPublisherById(id)
                .orElseThrow(BusinessLogicException::publisherNotFound);


        return ResponseEntity.ok(publisherMapper.toDto(publisherService.update(updatedPublisher, publisherRequest)));
    }

    @PostMapping
    public ResponseEntity<PublisherDto> create(@RequestBody PublisherRequest publisherRequest) {
        Publisher createdPublisher = publisherService.create(publisherRequest);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdPublisher.getId()))
                .body(publisherMapper.toDto(createdPublisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        log.info("Searching for publisher has id {} ", id);
        Publisher updatedPublisher = publisherService.findPublisherById(id)
                .orElseThrow(BusinessLogicException::publisherNotFound);

        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
