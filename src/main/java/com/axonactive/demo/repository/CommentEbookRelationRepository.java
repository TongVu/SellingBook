package com.axonactive.demo.repository;

import com.axonactive.demo.entity.Comment;
import com.axonactive.demo.entity.CommentEbookRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentEbookRelationRepository extends JpaRepository<CommentEbookRelation, Integer> {
    List<Comment> findCommentByEbookId(Integer id);
}
