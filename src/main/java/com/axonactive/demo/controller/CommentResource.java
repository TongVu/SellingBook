package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CommentRequest;
import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.CommentService;
import com.axonactive.demo.service.mapper.CommentMapper;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.entity.Comment;
import com.axonactive.demo.service.dto.commentDto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CommentResource.PATH)
public class CommentResource {
    public static final String PATH = "/api/comments";

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentService commentService;
    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAll() {
        return ResponseEntity.ok(commentMapper.toDtos(commentService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Comment comment = commentService.findCommentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found " + id));

        return ResponseEntity.ok(commentMapper.toDto(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable("id") Integer id,
                                             @RequestBody CommentRequest commentRequest) throws ResourceNotFoundException {
        Comment updatedComment = commentService.findCommentById(id)
                .orElseThrow(() -> new ResourceAccessException("Comment not found " + id));

        return ResponseEntity.ok(commentMapper.toDto(commentService.update(updatedComment, commentRequest)));
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CommentRequest commentRequest) throws ResourceNotFoundException {
        Comment createdComment = commentService.create(commentRequest);

        return ResponseEntity.created(URI.create(
                (PATH + "/" + createdComment.getId()))).body(commentMapper.toDto(createdComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Comment deletedComment = commentService.findCommentById(id)
                .orElseThrow(() -> new ResourceAccessException("Comment not found " + id));
        commentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
