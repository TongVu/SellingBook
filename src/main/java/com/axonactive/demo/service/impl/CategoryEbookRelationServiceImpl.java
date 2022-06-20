package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.CategoryEbookRelationRequest;
import com.axonactive.demo.entity.Category;
import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.repository.CategoryEbookRelationRepository;
import com.axonactive.demo.service.CategoryEbookRelationService;
import com.axonactive.demo.service.CategoryService;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryEbookRelationServiceImpl implements CategoryEbookRelationService {
    @Autowired
    private CategoryEbookRelationRepository categoryEbookRelationRepository;

    @Autowired
    private EbookService ebookService;

    @Autowired
    private CategoryService categoryService;

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
        CategoryEbookRelation deleteCategoryEbookRelation = categoryEbookRelationRepository.findById(id)
                .orElseThrow(BusinessLogicException::categoryEbookRelationNotFound);

        categoryEbookRelationRepository.deleteById(id);
    }

    @Override
    public CategoryEbookRelation update(CategoryEbookRelation updatedCategoryEbookRelation, CategoryEbookRelationRequest categoryEbookRelationRequest) {
        Ebook requestedEbook = ebookService.findEbookById(categoryEbookRelationRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        Category requestedCategory = categoryService.findCategoryById(categoryEbookRelationRequest.getCategoryId())
                .orElseThrow(BusinessLogicException::categoryNotFound);

        updatedCategoryEbookRelation.setCategory(requestedCategory);
        updatedCategoryEbookRelation.setEbook(requestedEbook);

        return categoryEbookRelationRepository.save(updatedCategoryEbookRelation);
    }

    @Override
    public CategoryEbookRelation create(CategoryEbookRelationRequest categoryEbookRelationRequest) {
        Ebook requestedEbook = ebookService.findEbookById(categoryEbookRelationRequest.getEbookId())
                .orElseThrow(BusinessLogicException::ebookNotFound);

        Category requestedCategory = categoryService.findCategoryById(categoryEbookRelationRequest.getCategoryId())
                .orElseThrow(BusinessLogicException::categoryNotFound);

        CategoryEbookRelation createdCategoryEbookRelation = new CategoryEbookRelation();
        createdCategoryEbookRelation.setCategory(requestedCategory);
        createdCategoryEbookRelation.setEbook(requestedEbook);
        return categoryEbookRelationRepository.save(createdCategoryEbookRelation);
    }

    @Override
    public Optional<CategoryEbookRelation> findCategoryEbookRelationById(Integer id) {
        return categoryEbookRelationRepository.findById(id);
    }

    @Override
    public List<CategoryEbookRelationDto> findEbookRatingGreaterThan(double ratingPoints) {
        return categoryEbookRelationRepository.findEbookRatingGreaterThan(ratingPoints);
    }

    @Override
    public List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCase(String categoryName) {
        return categoryEbookRelationRepository.findEbookByCategoryNameIgnoreCase(categoryName);
    }

    @Override
    public List<CategoryEbookRelation> findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan(String categoryName, double rating) {
        return categoryEbookRelationRepository.findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan(categoryName, rating);
    }

}
