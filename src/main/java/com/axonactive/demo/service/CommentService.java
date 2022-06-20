package com.axonactive.demo.service;

import com.axonactive.demo.controller.request.CommentRequest;
import com.axonactive.demo.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAll();

    Comment save(Comment comment);

    Comment update(Comment updatedComment, CommentRequest commentRequest);

    Comment create(CommentRequest commentRequest);

    void deleteById(Integer id);

    Optional<Comment> findCommentById(Integer id);
}
