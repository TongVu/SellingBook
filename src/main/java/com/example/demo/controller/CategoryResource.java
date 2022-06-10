package com.example.demo.controller;

import com.example.demo.controller.request.CategoryRequest;
import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CategoryService;
import com.example.demo.service.dto.categoryDto.CategoryDto;
import com.example.demo.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CategoryResource.PATH)
public class CategoryResource {
    public static final String PATH = "/api/categories";

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(){
        return ResponseEntity.ok(categoryMapper.toDtos(categoryService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Category category = categoryService.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        return ResponseEntity.ok(categoryMapper.toDto(category));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryRequest category){
        Category createdCategory = categoryService.save(
                new Category(
                        null,
                        category.getName(),
                        category.getNumberOfBooks()
                )
        );

        return ResponseEntity.created(URI.create(PATH + "/" + createdCategory.getId())).body(categoryMapper.toDto(createdCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable("id") Integer id,
                                              @RequestBody Category category) throws ResourceNotFoundException{
        Category updatedCategory = categoryService.findCategoryById(id)
                .orElseThrow(() -> new ResourceAccessException("Not found " + id));

        updatedCategory.setName(category.getName());
        updatedCategory.setNumberOfBooks(category.getNumberOfBooks());

        return ResponseEntity.ok(categoryMapper.toDto(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        Category deletedCategory = categoryService.findCategoryById(id)
                .orElseThrow(() -> new ResourceAccessException("Not found" + id));

        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}