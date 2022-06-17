package com.axonactive.demo.service;

import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;

import java.util.List;
import java.util.Optional;

public interface CategoryEbookRelationService {
    List<CategoryEbookRelation> getAll();

    CategoryEbookRelation save(CategoryEbookRelation categoryEbookRelation);

    void deleteById(Integer id);

    List<CategoryEbookRelationDto> findEbookRatingGreaterThan(double ratingPoints);

    List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCase(String categoryName);

    Optional<CategoryEbookRelation> findCategoryEbookRelationById(Integer id);

    List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan(String categoryName, double rating);

}
