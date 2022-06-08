package com.example.demo.service;
import com.example.demo.entity.CommentEbookRelation;

import java.util.List;
import java.util.Optional;

public interface CommentEbookRelationService {

    List<CommentEbookRelation> getAll();

    void save(CommentEbookRelation category);

    void deleteById(Integer id);

    Optional<CommentEbookRelation> findCommentEbookRelationById(Integer id);
}
