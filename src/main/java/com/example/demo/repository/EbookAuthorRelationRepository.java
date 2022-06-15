package com.example.demo.repository;

import com.example.demo.entity.EbookAuthorRelation;
import com.example.demo.service.dto.ebookAuthorRelationDto.EbookAuthorRelationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EbookAuthorRelationRepository extends JpaRepository<EbookAuthorRelation, Integer> {
    List<EbookAuthorRelation> findByAuthorLastNameContainingIgnoreCase(String authorname);
}
