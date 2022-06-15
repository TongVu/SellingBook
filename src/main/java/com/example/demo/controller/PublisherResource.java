package com.example.demo.controller;

import com.example.demo.controller.request.PublisherRequest;
import com.example.demo.entity.Publisher;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.PublisherService;
import com.example.demo.service.dto.publisherDto.PublisherDto;
import com.example.demo.service.mapper.PublisherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<PublisherDto> getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Publisher publisher = publisherService.findPublisherById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        return ResponseEntity.ok(publisherMapper.toDto(publisher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDto> update(@PathVariable("id") Integer id,
                                               @RequestBody PublisherRequest publisherRequest) throws ResourceNotFoundException {
        Publisher updatedPublisher = publisherService.findPublisherById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        updatedPublisher.setName(publisherRequest.getName());
        updatedPublisher.setPhone(publisherRequest.getPhone());
        updatedPublisher.setAddress(publisherRequest.getAddress());
        updatedPublisher.setEmail(publisherRequest.getEmail());


        return ResponseEntity.ok(publisherMapper.toDto(publisherService.save(updatedPublisher)));
    }

    @PostMapping
    public ResponseEntity<PublisherDto> create(@RequestBody PublisherRequest publisherRequest) {
        Publisher createdPublisher = new Publisher();
        createdPublisher.setName(publisherRequest.getName());
        createdPublisher.setPhone(publisherRequest.getPhone());
        createdPublisher.setAddress(publisherRequest.getAddress());
        createdPublisher.setEmail(publisherRequest.getEmail());

        publisherService.save(createdPublisher);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdPublisher.getId()))
                .body(publisherMapper.toDto(createdPublisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Publisher updatedPublisher = publisherService.findPublisherById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
