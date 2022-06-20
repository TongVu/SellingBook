package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CommentEbookRelationRequest;
import com.axonactive.demo.entity.CommentEbookRelation;
import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.service.CommentEbookRelationService;
import com.axonactive.demo.service.CommentService;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.service.dto.commentEbookRelationDto.CommentEbookRelationDto;
import com.axonactive.demo.service.mapper.CommentEbookRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CommentEbookRelationResource.PATH)
public class CommentEbookRelationResource {
    public static final String PATH = "/api/commentebookrelations";

    @Autowired
    CommentEbookRelationMapper commentEbookRelationMapper;

    @Autowired
    CommentEbookRelationService commentEbookRelationService;

    @Autowired
    EbookService ebookService;

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentEbookRelationDto>> getAll() {
        return ResponseEntity.ok(commentEbookRelationMapper.toDtos(commentEbookRelationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentEbookRelationDto> getById(@PathVariable Integer id) throws ResourceNotFoundException {
        CommentEbookRelation foundComment = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        return ResponseEntity.ok(commentEbookRelationMapper.toDto(foundComment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentEbookRelationDto> update(@PathVariable("id") Integer id,
                                                          @RequestBody CommentEbookRelationRequest commentEbookRelationRequest) throws ResourceNotFoundException {
        CommentEbookRelation commentEbookRelation = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(() -> new ResourceAccessException("Not found " + id));


        return ResponseEntity
                .ok(commentEbookRelationMapper.toDto(commentEbookRelationService.update(commentEbookRelation, commentEbookRelationRequest)));
    }

    @PostMapping
    public ResponseEntity<CommentEbookRelationDto> create(@RequestBody CommentEbookRelationRequest commentEbookRelationRequest) throws ResourceNotFoundException {

        CommentEbookRelation createdCommentEbookRelation = commentEbookRelationService.create(commentEbookRelationRequest);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdCommentEbookRelation.getId()))
                .body(commentEbookRelationMapper.toDto(createdCommentEbookRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        CommentEbookRelation deletedCommentEbookRelation = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
        commentEbookRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
