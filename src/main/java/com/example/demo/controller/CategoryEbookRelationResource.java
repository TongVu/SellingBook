package com.example.demo.controller;

import com.example.demo.controller.request.CategoryEbookRelationRequest;
import com.example.demo.controller.request.CategoryRequest;
import com.example.demo.entity.Author;
import com.example.demo.entity.Category;
import com.example.demo.entity.CategoryEbookRelation;
import com.example.demo.entity.Ebook;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CategoryEbookRelationService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.EbookService;
import com.example.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import com.example.demo.service.mapper.CategoryEbookRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CategoryEbookRelationResource.PATH)
public class CategoryEbookRelationResource {
    public static final String PATH = "/api/categoryebookrelations";

    @Autowired
    CategoryEbookRelationService categoryEbookRelationService;
    @Autowired
    CategoryEbookRelationMapper categoryEbookRelationMapper;
    @Autowired
    EbookService ebookService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryEbookRelationDto>> getAll(){
        return ResponseEntity.ok(categoryEbookRelationMapper.toDtos(categoryEbookRelationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEbookRelationDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        CategoryEbookRelation foundCategoryEbookRelation = categoryEbookRelationService.findCategoryEbookRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        return ResponseEntity.ok(categoryEbookRelationMapper.toDto(foundCategoryEbookRelation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryEbookRelationDto> update(@PathVariable("id") Integer id,
                                                           @RequestBody CategoryEbookRelationRequest categoryEbookRelationRequest) throws ResourceNotFoundException{
        Ebook requestedEbook = ebookService.findEbookById(categoryEbookRelationRequest.getEbookId())
                .orElseThrow(()-> new ResourceNotFoundException("Ebook not found " + categoryEbookRelationRequest.getEbookId()));

        Category requestedCategory = categoryService.findCategoryById(categoryEbookRelationRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found " + categoryEbookRelationRequest.getCategoryId()));

        CategoryEbookRelation updatedCategoryEbookRelation = categoryEbookRelationService.findCategoryEbookRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        updatedCategoryEbookRelation.setCategory(requestedCategory);
        updatedCategoryEbookRelation.setEbook(requestedEbook);
        categoryEbookRelationService.save(updatedCategoryEbookRelation);

        return ResponseEntity.ok(categoryEbookRelationMapper.toDto(categoryEbookRelationService.save(updatedCategoryEbookRelation)));
    }

    @PostMapping
    public ResponseEntity<CategoryEbookRelationDto> create(@RequestBody CategoryEbookRelationRequest categoryEbookRelationRequest) throws ResourceNotFoundException {
        Ebook requestedEbook = ebookService.findEbookById(categoryEbookRelationRequest.getEbookId())
                .orElseThrow(()-> new ResourceNotFoundException("Ebook not found " + categoryEbookRelationRequest.getEbookId()));
        Category requestedCategory = categoryService.findCategoryById(categoryEbookRelationRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found " + categoryEbookRelationRequest.getCategoryId()));

        CategoryEbookRelation createdCategoryEbookRelation =categoryEbookRelationService.save(
                new CategoryEbookRelation(
                        null,
                        requestedEbook,
                        requestedCategory
                )
        );

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdCategoryEbookRelation.getId()))
                .body(categoryEbookRelationMapper.toDto(createdCategoryEbookRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        CategoryEbookRelation deleteCategoryEbookRelation = categoryEbookRelationService.findCategoryEbookRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found "+ id));
        categoryEbookRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}