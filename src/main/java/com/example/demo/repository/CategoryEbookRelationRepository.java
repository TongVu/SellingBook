package com.example.demo.repository;

import com.example.demo.entity.CategoryEbookRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryEbookRelationRepository extends JpaRepository<CategoryEbookRelation, Integer> {
}
