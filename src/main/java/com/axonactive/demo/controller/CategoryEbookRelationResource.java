package com.axonactive.demo.controller;

import com.axonactive.demo.exception.ResourceNotFoundException;
import com.axonactive.demo.service.CategoryService;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import com.axonactive.demo.service.mapper.CategoryEbookRelationMapper;
import com.axonactive.demo.controller.request.CategoryEbookRelationRequest;
import com.axonactive.demo.entity.Category;
import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.entity.Ebook;
import com.axonactive.demo.service.CategoryEbookRelationService;
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
    public ResponseEntity<List<CategoryEbookRelationDto>> getAll() {
        return ResponseEntity.ok(categoryEbookRelationMapper.toDtos(categoryEbookRelationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEbookRelationDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        CategoryEbookRelation foundCategoryEbookRelation = categoryEbookRelationService.findCategoryEbookRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        return ResponseEntity.ok(categoryEbookRelationMapper.toDto(foundCategoryEbookRelation));
    }

    @GetMapping("/find")
    public ResponseEntity<?> getEbooksHaveRatingGreaterThan(@RequestParam(value = "rating", required = false) Double ratingPoints,
                                                            @RequestParam(value = "category", defaultValue = "empty", required = false) String categoryName) {
        if(ratingPoints != null &&
                !categoryName.equals("empty"))
            return ResponseEntity.ok(categoryEbookRelationMapper.toDtos(
                    categoryEbookRelationService.findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan(categoryName, ratingPoints)));

        if (ratingPoints != null)
            return ResponseEntity.ok(categoryEbookRelationService.findEbookRatingGreaterThan(ratingPoints));

        if (!categoryName.equals("empty"))
            return ResponseEntity.ok(
                    categoryEbookRelationMapper.toDtos(
                            categoryEbookRelationService.findEbookByCategoryNameIgnoreCase(categoryName)));

        return ResponseEntity.ok("Please provide Request param");
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryEbookRelationDto> update(@PathVariable("id") Integer id,
                                                           @RequestBody CategoryEbookRelationRequest categoryEbookRelationRequest) throws ResourceNotFoundException {
        Ebook requestedEbook = ebookService.findEbookById(categoryEbookRelationRequest.getEbookId())
                .orElseThrow(() -> new ResourceNotFoundException("Ebook not found " + categoryEbookRelationRequest.getEbookId()));

        Category requestedCategory = categoryService.findCategoryById(categoryEbookRelationRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found " + categoryEbookRelationRequest.getCategoryId()));

        CategoryEbookRelation updatedCategoryEbookRelation = categoryEbookRelationService.findCategoryEbookRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));

        updatedCategoryEbookRelation.setCategory(requestedCategory);
        updatedCategoryEbookRelation.setEbook(requestedEbook);

        return ResponseEntity.ok(categoryEbookRelationMapper.toDto(categoryEbookRelationService.save(updatedCategoryEbookRelation)));
    }

    @PostMapping
    public ResponseEntity<CategoryEbookRelationDto> create(@RequestBody CategoryEbookRelationRequest categoryEbookRelationRequest) throws ResourceNotFoundException {

        Ebook requestedEbook = ebookService.findEbookById(categoryEbookRelationRequest.getEbookId())
                .orElseThrow(() -> new ResourceNotFoundException("Ebook not found " + categoryEbookRelationRequest.getEbookId()));

        Category requestedCategory = categoryService.findCategoryById(categoryEbookRelationRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found " + categoryEbookRelationRequest.getCategoryId()));

        CategoryEbookRelation createdCategoryEbookRelation = new CategoryEbookRelation();
        createdCategoryEbookRelation.setCategory(requestedCategory);
        createdCategoryEbookRelation.setEbook(requestedEbook);
        categoryEbookRelationService.save(createdCategoryEbookRelation);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdCategoryEbookRelation.getId()))
                .body(categoryEbookRelationMapper.toDto(createdCategoryEbookRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        CategoryEbookRelation deleteCategoryEbookRelation = categoryEbookRelationService.findCategoryEbookRelationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
        categoryEbookRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}