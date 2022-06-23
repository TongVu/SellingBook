package com.axonactive.demo.service;

import com.axonactive.demo.controller.request.CategoryEbookRelationRequest;
import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CategoryEbookRelationService {
    List<CategoryEbookRelation> getAll();

    CategoryEbookRelation save(CategoryEbookRelation categoryEbookRelation);

    void deleteById(Integer id);

    CategoryEbookRelation update(CategoryEbookRelation categoryEbookRelation, CategoryEbookRelationRequest categoryEbookRelationRequest);

    CategoryEbookRelation create(CategoryEbookRelationRequest categoryEbookRelationRequest);

    List<CategoryEbookRelationDto> findEbookRatingGreaterThan(double ratingPoints);

    List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCase(String categoryName);

    Optional<CategoryEbookRelation> findCategoryEbookRelationById(Integer id);

    List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan(String categoryName, double rating);

}
