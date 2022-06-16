package com.axonactive.demo.service.impl;

import com.axonactive.demo.repository.CategoryEbookRelationRepository;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.service.CategoryEbookRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryEbookRelationServiceImpl implements CategoryEbookRelationService {
    @Autowired
    private final CategoryEbookRelationRepository categoryEbookRelationRepository;

    @Override
    public List<CategoryEbookRelation> getAll() {
        return categoryEbookRelationRepository.findAll();
    }

    @Override
    public CategoryEbookRelation save(CategoryEbookRelation categoryEbookRelation) {
        return categoryEbookRelationRepository.save(categoryEbookRelation);
    }

    @Override
    public void deleteById(Integer id) {
        categoryEbookRelationRepository.deleteById(id);
    }

    @Override
    public Optional<CategoryEbookRelation> findCategoryEbookRelationById(Integer id) {
        return categoryEbookRelationRepository.findById(id);
    }

    @Override
    public List<CategoryEbookRelationDto> findEbookByRating(double ratingPoints) {
        return categoryEbookRelationRepository.findEbookByRating(ratingPoints);
    }

    @Override
    public List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCase(String categoryName) {
        return categoryEbookRelationRepository.findEbookByCategoryNameIgnoreCase(categoryName);
    }

    @Override
    public List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan(String categoryName, double rating) {
        return categoryEbookRelationRepository
                .findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan(categoryName, rating);
    }
}