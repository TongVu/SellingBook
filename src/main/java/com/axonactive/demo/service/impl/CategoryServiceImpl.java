package com.axonactive.demo.service.impl;

import com.axonactive.demo.controller.request.CategoryRequest;
import com.axonactive.demo.entity.Category;
import com.axonactive.demo.repository.CategoryRepository;
import com.axonactive.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category updatedCategory, CategoryRequest categoryRequest) {
        updatedCategory.setName(categoryRequest.getName());
        updatedCategory.setNumberOfBooks(categoryRequest.getNumberOfBooks());

        return categoryRepository.save(updatedCategory);
    }

    @Override
    public Category create(CategoryRequest categoryRequest) {
        Category createdCategory = new Category();

        createdCategory.setName(categoryRequest.getName());
        createdCategory.setNumberOfBooks(categoryRequest.getNumberOfBooks());

        return categoryRepository.save(createdCategory);
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
}
