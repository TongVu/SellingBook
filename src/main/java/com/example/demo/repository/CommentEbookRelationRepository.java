package com.example.demo.repository;

import com.example.demo.entity.CommentEbookRelation;
import com.example.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentEbookRelationRepository extends JpaRepository<CommentEbookRelation, Integer> {



}
