package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CommentRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.entity.Comment;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.CommentService;
import com.axonactive.demo.service.dto.commentDto.CommentDto;
import com.axonactive.demo.service.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CommentDto> getById(@PathVariable("id") Integer id) {
        Comment comment = commentService.findCommentById(id)
                .orElseThrow(BusinessLogicException::commenNotFound);

        return ResponseEntity.ok(commentMapper.toDto(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable("id") Integer id,
                                             @RequestBody CommentRequest commentRequest) {
        Account requestedAccount = accountService.findAccountById(id)
                .orElseThrow(BusinessLogicException::accountNotFound);

        Comment updatedComment = commentService.findCommentById(id)
                .orElseThrow(BusinessLogicException::commenNotFound);

        updatedComment.setCommentContent(commentRequest.getCommentContent());
        updatedComment.setBookRating(commentRequest.getBookRating());
        updatedComment.setCommentUpvote(commentRequest.getCommentUpvote());
        updatedComment.setDate(commentRequest.getDate());
        updatedComment.setAccount(requestedAccount);
        commentService.save(updatedComment);

        return ResponseEntity.ok(commentMapper.toDto(commentService.save(updatedComment)));
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CommentRequest commentRequest) {
        Account requestedAccount = accountService.findAccountById(commentRequest.getAccountId())
                .orElseThrow(BusinessLogicException::accountNotFound);

        Comment createdComment = new Comment();
        createdComment.setCommentContent(commentRequest.getCommentContent());
        createdComment.setBookRating(commentRequest.getBookRating());
        createdComment.setCommentUpvote(commentRequest.getCommentUpvote());
        createdComment.setDate(commentRequest.getDate());
        createdComment.setAccount(requestedAccount);

        commentService.save(createdComment);

        return ResponseEntity.created(URI.create(
                (PATH + "/" + createdComment.getId()))).body(commentMapper.toDto(createdComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Comment deletedComment = commentService.findCommentById(id)
                .orElseThrow(BusinessLogicException::commenNotFound);
        commentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
