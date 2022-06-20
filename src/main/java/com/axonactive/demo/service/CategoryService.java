package com.axonactive.demo.service;

import com.axonactive.demo.controller.request.CategoryRequest;
import com.axonactive.demo.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();

    Category save(Category category);

    void deleteById(Integer id);

    Category update(Category updatedCategory, CategoryRequest categoryRequest);

    Category create(CategoryRequest categoryRequest);

    Optional<Category> findCategoryById(Integer id);
}
