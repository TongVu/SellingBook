package com.example.demo.service.impl;

import com.example.demo.entity.CategoryEbookRelation;
import com.example.demo.repository.CategoryEbookRelationRepository;
import com.example.demo.service.CategoryEbookRelationService;
import com.example.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
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
    public List<CategoryEbookRelationDto> findEbookByRating(Integer ratingPoints) {
        return categoryEbookRelationRepository.findEbookByRating(ratingPoints);
    }
}
