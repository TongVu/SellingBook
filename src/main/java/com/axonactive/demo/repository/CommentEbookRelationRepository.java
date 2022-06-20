package com.axonactive.demo.repository;

import com.axonactive.demo.entity.CommentEbookRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentEbookRelationRepository extends JpaRepository<CommentEbookRelation, Integer> {


}
