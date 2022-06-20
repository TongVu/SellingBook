package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CommentEbookRelationRequest;
import com.axonactive.demo.entity.CommentEbookRelation;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.CommentEbookRelationService;
import com.axonactive.demo.service.dto.commentEbookRelationDto.CommentEbookRelationDto;
import com.axonactive.demo.service.mapper.CommentEbookRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(CommentEbookRelationResource.PATH)
public class CommentEbookRelationResource {
    public static final String PATH = "/api/commentebookrelations";

    @Autowired
    CommentEbookRelationMapper commentEbookRelationMapper;

    @Autowired
    CommentEbookRelationService commentEbookRelationService;

    @GetMapping
    public ResponseEntity<List<CommentEbookRelationDto>> getAll() {
        return ResponseEntity.ok(commentEbookRelationMapper.toDtos(commentEbookRelationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentEbookRelationDto> getById(@PathVariable Integer id) {
        log.info("Searching for comment_ebook_relation has id {} ", id);
        CommentEbookRelation foundComment = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(BusinessLogicException::commenEbookRelationNotFound);

        return ResponseEntity.ok(commentEbookRelationMapper.toDto(foundComment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentEbookRelationDto> update(@PathVariable("id") Integer id,
                                                          @RequestBody CommentEbookRelationRequest commentEbookRelationRequest) {
        log.info("Searching for comment_ebook_relation has id {} ", id);
        CommentEbookRelation commentEbookRelation = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(BusinessLogicException::commenEbookRelationNotFound);

        return ResponseEntity
                .ok(commentEbookRelationMapper.toDto(commentEbookRelationService.update(commentEbookRelation, commentEbookRelationRequest)));
    }

    @PostMapping
    public ResponseEntity<CommentEbookRelationDto> create(@RequestBody CommentEbookRelationRequest commentEbookRelationRequest) {
        CommentEbookRelation createdCommentEbookRelation = commentEbookRelationService.create(commentEbookRelationRequest);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdCommentEbookRelation.getId()))
                .body(commentEbookRelationMapper.toDto(createdCommentEbookRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        log.info("Searching for comment_ebook_relation has id {} ", id);
        CommentEbookRelation deletedCommentEbookRelation = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(BusinessLogicException::commenEbookRelationNotFound);
        commentEbookRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
