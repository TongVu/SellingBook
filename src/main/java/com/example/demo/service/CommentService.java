package com.example.demo.service;

import com.example.demo.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> getAll();

    void save(Comment comment);

    void deleteById(Integer id);

    Optional<Comment> findCommentById(Integer id);
}
