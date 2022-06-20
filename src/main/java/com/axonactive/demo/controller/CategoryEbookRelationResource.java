package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CategoryEbookRelationRequest;
import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.CategoryEbookRelationService;
import com.axonactive.demo.service.CategoryService;
import com.axonactive.demo.service.EbookService;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import com.axonactive.demo.service.mapper.CategoryEbookRelationMapper;
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
    public ResponseEntity<CategoryEbookRelationDto> getById(@PathVariable(value = "id") Integer id) {
        CategoryEbookRelation foundCategoryEbookRelation = categoryEbookRelationService.findCategoryEbookRelationById(id)
                .orElseThrow(BusinessLogicException::categoryEbookRelationNotFound);

        return ResponseEntity.ok(categoryEbookRelationMapper.toDto(foundCategoryEbookRelation));
    }

    @GetMapping("/find")
    public ResponseEntity<?> getEbooksHaveRatingGreaterThan(@RequestParam(value = "rating", required = false) Double ratingPoints,
                                                            @RequestParam(value = "category", defaultValue = "empty", required = false) String categoryName) {
        if (ratingPoints != null &&
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
                                                           @RequestBody CategoryEbookRelationRequest categoryEbookRelationRequest) {
        CategoryEbookRelation updatedCategoryEbookRelation = categoryEbookRelationService.findCategoryEbookRelationById(id)
                .orElseThrow(BusinessLogicException::categoryEbookRelationNotFound);

        CategoryEbookRelation categoryEbookRelation = categoryEbookRelationService.update(updatedCategoryEbookRelation, categoryEbookRelationRequest);

        return ResponseEntity.ok(categoryEbookRelationMapper.toDto(categoryEbookRelation));
    }

    @PostMapping
    public ResponseEntity<CategoryEbookRelationDto> create(@RequestBody CategoryEbookRelationRequest categoryEbookRelationRequest) {
        CategoryEbookRelation createdCategoryEbookRelation = categoryEbookRelationService.create(categoryEbookRelationRequest);

        return ResponseEntity
                .created(URI.create(PATH + "/" + createdCategoryEbookRelation.getId()))
                .body(categoryEbookRelationMapper.toDto(createdCategoryEbookRelation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        categoryEbookRelationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}