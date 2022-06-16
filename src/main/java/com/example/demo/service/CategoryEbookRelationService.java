package com.example.demo.service;

import com.example.demo.entity.CategoryEbookRelation;
import com.example.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryEbookRelationService {
    List<CategoryEbookRelation> getAll();

    CategoryEbookRelation save(CategoryEbookRelation categoryEbookRelation);

    void deleteById(Integer id);

    Optional<CategoryEbookRelation> findCategoryEbookRelationById(Integer id);

    List<CategoryEbookRelationDto> findEbookByRating(double ratingPoints);

    List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCase(String categoryName);

    List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan(String categoryName, double rating);
}
