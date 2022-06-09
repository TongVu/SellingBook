package com.example.demo.service;

import com.example.demo.entity.CategoryEbookRelation;

import java.util.List;
import java.util.Optional;

public interface CategoryEbookRelationService {
    List<CategoryEbookRelation> getAll();

    CategoryEbookRelation save(CategoryEbookRelation categoryEbookRelation);

    void deleteById(Integer id);

    Optional<CategoryEbookRelation> findCategoryEbookRelationById(Integer id);
}
