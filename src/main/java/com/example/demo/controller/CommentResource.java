package com.example.demo.controller;

import com.example.demo.controller.request.CommentRequest;
import com.example.demo.entity.Account;
import com.example.demo.entity.Comment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.AccountService;
import com.example.demo.service.CommentService;
import com.example.demo.service.dto.commentDto.CommentDto;
import com.example.demo.service.mapper.CommentMapper;
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
    public ResponseEntity<List<CommentDto>> getAll(){
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
                                             @RequestBody CommentRequest commentRequest) throws ResourceNotFoundException{
        Account requestedAccount = accountService.findAccountById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found " + commentRequest.getAccountId()));

        Comment updatedComment = commentService.findCommentById(id)
                .orElseThrow(() -> new ResourceAccessException("Comment not found " + id));

        updatedComment.setCommentContent(commentRequest.getCommentContent());
        updatedComment.setBookRating(commentRequest.getBookRating());
        updatedComment.setCommentUpvote(commentRequest.getCommentUpvote());
        updatedComment.setDate(commentRequest.getDate());
        updatedComment.setAccount(requestedAccount);
        commentService.save(updatedComment);

        return ResponseEntity.ok(commentMapper.toDto(commentService.save(updatedComment)));
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody Comment comment){
        Comment createdComment = commentService.save(
                new Comment(
                        null,
                        comment.getCommentContent(),
                        comment.getBookRating(),
                        comment.getCommentUpvote(),
                        comment.getDate(),
                        comment.getAccount()
                )
        );

        return ResponseEntity.created(URI.create(
                (PATH + "/" + createdComment.getId()))).body(commentMapper.toDto(createdComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        Comment deletedComment = commentService.findCommentById(id)
                .orElseThrow(() -> new ResourceAccessException("Comment not found " + id));
        commentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}