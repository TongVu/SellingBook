package com.axonactive.demo.service;

import com.axonactive.demo.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();

    Category save(Category category);

    void deleteById(Integer id);

    Optional<Category> findCategoryById(Integer id);
}
