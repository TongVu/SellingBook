package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CommentRequest;
import com.axonactive.demo.entity.Comment;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.CommentService;
import com.axonactive.demo.service.dto.commentDto.CommentDto;
import com.axonactive.demo.service.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(CommentResource.PATH)
public class CommentResource {
    public static final String PATH = "/api/comments";

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAll() {
        return ResponseEntity.ok(commentMapper.toDtos(commentService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getById(@PathVariable("id") Integer id) {
        log.info("Searching for comment has id {} ", id);
        Comment comment = commentService.findCommentById(id)
                .orElseThrow(BusinessLogicException::commenNotFound);

        return ResponseEntity.ok(commentMapper.toDto(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable("id") Integer id,
                                             @RequestBody CommentRequest commentRequest) {
        log.info("Searching for comment has id {} ", id);
        Comment updatedComment = commentService.findCommentById(id)
                .orElseThrow(BusinessLogicException::commenNotFound);

        return ResponseEntity.ok(commentMapper.toDto(commentService.update(updatedComment, commentRequest)));
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CommentRequest commentRequest) {
        Comment createdComment = commentService.create(commentRequest);

        return ResponseEntity.created(URI.create(
                (PATH + "/" + createdComment.getId()))).body(commentMapper.toDto(createdComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        log.info("Searching for comment has id {} ", id);
        Comment deletedComment = commentService.findCommentById(id)
                .orElseThrow(BusinessLogicException::commenNotFound);
        commentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
