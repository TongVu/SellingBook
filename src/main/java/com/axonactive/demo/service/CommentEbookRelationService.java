package com.axonactive.demo.service;
import com.axonactive.demo.entity.CommentEbookRelation;

import java.util.List;
import java.util.Optional;

public interface CommentEbookRelationService {

    List<CommentEbookRelation> getAll();

    CommentEbookRelation save(CommentEbookRelation category);

    void deleteById(Integer id);

    Optional<CommentEbookRelation> findCommentEbookRelationById(Integer id);
}
