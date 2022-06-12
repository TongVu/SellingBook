package com.example.demo.controller;

import com.example.demo.controller.request.CommentEbookRelationRequest;
import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentEbookRelation;
import com.example.demo.entity.Ebook;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CommentEbookRelationService;
import com.example.demo.service.CommentService;
import com.example.demo.service.EbookService;
import com.example.demo.service.dto.commentEbookRelationDto.CommentEbookRelationDto;
import com.example.demo.service.mapper.CommentEbookRelationMapper;
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
    public ResponseEntity<List<CommentEbookRelationDto>> getAll(){
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
                                                          @RequestBody CommentEbookRelationRequest commentEbookRelationRequest) throws ResourceNotFoundException{
        CommentEbookRelation commentEbookRelation = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(() -> new ResourceAccessException("Not found " + id));

        commentEbookRelation.getComment().setId(commentEbookRelationRequest.getCommentId());
        commentEbookRelation.getEbook().setId(commentEbookRelationRequest.getEbookId());
        commentEbookRelationService.save(commentEbookRelation);

        return ResponseEntity.ok(commentEbookRelationMapper.toDto(commentEbookRelationService.save(commentEbookRelation)));
    }

    @PostMapping
    public ResponseEntity<CommentEbookRelationDto> create(@RequestBody CommentEbookRelationRequest commentEbookRelationRequest) throws ResourceNotFoundException{
        Comment comment = commentService.findCommentById(commentEbookRelationRequest.getCommentId())
                .orElseThrow(() -> new ResourceAccessException("Comment not found " + commentEbookRelationRequest.getCommentId()));

        Ebook ebook = ebookService.findEbookById(commentEbookRelationRequest.getEbookId())
                .orElseThrow(() -> new ResourceAccessException("Ebook not found " + commentEbookRelationRequest.getEbookId()));

        CommentEbookRelation newCommentEbookRelation =  commentEbookRelationService.save(
                new CommentEbookRelation(null,
                        comment,
                        ebook));

        return ResponseEntity
                .created(URI.create(PATH + "/" + newCommentEbookRelation.getId()))
                .body(commentEbookRelationMapper.toDto(newCommentEbookRelation));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)throws ResourceNotFoundException{
        CommentEbookRelation deletedCommentEbookRelation = commentEbookRelationService.findCommentEbookRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
        commentEbookRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
