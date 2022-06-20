package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CommentEbookRelationRequest;
import com.axonactive.demo.entity.Comment;
import com.axonactive.demo.entity.CommentEbookRelation;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.CommentEbookRelationService;
import com.axonactive.demo.service.CommentService;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.service.dto.commentEbookRelationDto.CommentEbookRelationDto;
import com.axonactive.demo.service.mapper.CommentEbookRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CommentEbookRelationDto> getById(@PathVariable Integer id) {
        CommentEbookRelation foundComment = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(BusinessLogicException::commenEbookRelationNotFound);

        return ResponseEntity.ok(commentEbookRelationMapper.toDto(foundComment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentEbookRelationDto> update(@PathVariable("id") Integer id,
                                                          @RequestBody CommentEbookRelationRequest commentEbookRelationRequest) {
        CommentEbookRelation commentEbookRelation = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(BusinessLogicException::commenEbookRelationNotFound);

        Comment requestedComment = commentService.findCommentById(commentEbookRelationRequest.getCommentId())
                .orElseThrow(BusinessLogicException::commenNotFound);

        Ebook requetedEbook = ebookService.findEbookById(commentEbookRelationRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        commentEbookRelation.setComment(requestedComment);
        commentEbookRelation.setEbook(requetedEbook);

        return ResponseEntity
                .ok(commentEbookRelationMapper.toDto(commentEbookRelationService.save(commentEbookRelation)));
    }

    @PostMapping
    public ResponseEntity<CommentEbookRelationDto> create(@RequestBody CommentEbookRelationRequest commentEbookRelationRequest) {
        Comment comment = commentService.findCommentById(commentEbookRelationRequest.getCommentId())
                .orElseThrow(BusinessLogicException::commenNotFound);

        Ebook ebook = ebookService.findEbookById(commentEbookRelationRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        CommentEbookRelation createdCommentEbookRelation = new CommentEbookRelation();
        createdCommentEbookRelation.setComment(comment);
        createdCommentEbookRelation.setEbook(ebook);

        commentEbookRelationService.save(createdCommentEbookRelation);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdCommentEbookRelation.getId()))
                .body(commentEbookRelationMapper.toDto(createdCommentEbookRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        CommentEbookRelation deletedCommentEbookRelation = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(BusinessLogicException::commenEbookRelationNotFound);
        commentEbookRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
