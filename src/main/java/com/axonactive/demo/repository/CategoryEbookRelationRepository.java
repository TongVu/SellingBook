package com.axonactive.demo.repository;

import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryEbookRelationRepository extends JpaRepository<CategoryEbookRelation, Integer> {
    @Query(nativeQuery = true)
    List<CategoryEbookRelationDto> findEbookByRating(double ratingPoints);

    List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCase(String categoryName);

    List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan(String categoryName, double rating);
}
