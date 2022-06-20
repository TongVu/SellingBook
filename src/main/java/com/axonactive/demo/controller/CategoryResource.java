package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CategoryRequest;
import com.axonactive.demo.entity.Category;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.CategoryService;
import com.axonactive.demo.service.dto.categoryDto.CategoryDto;
import com.axonactive.demo.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryMapper.toDtos(categoryService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable(value = "id") Integer id) {
        Category category = categoryService.findCategoryById(id)
                .orElseThrow(BusinessLogicException::categoryNotFound);

        return ResponseEntity.ok(categoryMapper.toDto(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable("id") Integer id,
                                              @RequestBody CategoryRequest categoryRequest) {
        Category updatedCategory = categoryService.findCategoryById(id)
                .orElseThrow(BusinessLogicException::categoryNotFound);

        updatedCategory.setName(categoryRequest.getName());
        updatedCategory.setNumberOfBooks(categoryRequest.getNumberOfBooks());

        return ResponseEntity.ok(categoryMapper.toDto(categoryService.save(updatedCategory)));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryRequest category) {
        Category createdCategory = new Category();
        createdCategory.setName(category.getName());
        createdCategory.setNumberOfBooks(category.getNumberOfBooks());

        categoryService.save(createdCategory);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdCategory.getId()))
                .body(categoryMapper.toDto(createdCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        Category deletedCategory = categoryService.findCategoryById(id)
                .orElseThrow(BusinessLogicException::categoryNotFound);

        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
