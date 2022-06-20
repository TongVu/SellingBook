package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.CommentRequest;
import com.axonactive.demo.entity.Account;
import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.repository.CommentRepository;
import com.axonactive.demo.service.AccountService;
import com.axonactive.demo.service.CommentService;
import com.axonactive.demo.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment updatedComment, CommentRequest commentRequest) throws ResourceNotFoundException {
        Account requestedAccount = accountService.findAccountById(commentRequest.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found " + commentRequest.getAccountId()));

        updatedComment.setCommentContent(commentRequest.getCommentContent());
        updatedComment.setBookRating(commentRequest.getBookRating());
        updatedComment.setCommentUpvote(commentRequest.getCommentUpvote());
        updatedComment.setDate(commentRequest.getDate());
        updatedComment.setAccount(requestedAccount);

        return commentRepository.save(updatedComment);
    }

    @Override
    public Comment create(CommentRequest commentRequest) {
        Account requestedAccount = accountService.findAccountById(commentRequest.getAccountId())
                .orElseThrow(() -> new ResourceAccessException("Account not found " + commentRequest.getAccountId()));

        Comment createdComment = new Comment();
        createdComment.setCommentContent(commentRequest.getCommentContent());
        createdComment.setBookRating(commentRequest.getBookRating());
        createdComment.setCommentUpvote(commentRequest.getCommentUpvote());
        createdComment.setDate(commentRequest.getDate());
        createdComment.setAccount(requestedAccount);

        return commentRepository.save(createdComment);
    }

    @Override
    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<Comment> findCommentById(Integer id) {
        return commentRepository.findById(id);
    }
}
