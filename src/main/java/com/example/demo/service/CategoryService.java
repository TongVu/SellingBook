package com.example.demo.service;

import com.example.demo.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();

    Category save(Category category);

    void deleteById(Integer id);

    Optional<Category> findCategoryById(Integer id);
}
