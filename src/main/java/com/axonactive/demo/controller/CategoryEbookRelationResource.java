package com.axonactive.demo.controller;

import com.axonactive.demo.controller.request.CategoryEbookRelationRequest;
import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.exception.BusinessLogicException;
import com.axonactive.demo.service.CategoryEbookRelationService;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import com.axonactive.demo.service.mapper.CategoryEbookRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@Slf4j
@RequestMapping(CategoryEbookRelationResource.PATH)
@RestController
@Validated
public class CategoryEbookRelationResource {
    public static final String PATH = "/api/categoryebookrelations";

    @Autowired
    CategoryEbookRelationService categoryEbookRelationService;

    @Autowired
    CategoryEbookRelationMapper categoryEbookRelationMapper;

    @GetMapping
    public ResponseEntity<List<CategoryEbookRelationDto>> getAll() {
        return ResponseEntity.ok(categoryEbookRelationMapper.toDtos(categoryEbookRelationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEbookRelationDto> getById(@PathVariable(value = "id") Integer id) {
        log.info("Searching for category_ebook_relation has id {} ", id);
        CategoryEbookRelation foundCategoryEbookRelation = categoryEbookRelationService.findCategoryEbookRelationById(id)
                .orElseThrow(BusinessLogicException::categoryEbookRelationNotFound);

        return ResponseEntity.ok(categoryEbookRelationMapper.toDto(foundCategoryEbookRelation));
    }

    @GetMapping("/find")
    public ResponseEntity<?> getEbooksHaveRatingGreaterThan(@RequestParam Double ratingPoints,
                                                            @RequestParam(value = "category", defaultValue = "empty", required = false) String categoryName) {
        if(ratingPoints < 1)
            return ResponseEntity.badRequest().body("rating point has to be more than 1");
        if(ratingPoints > 5)
            return ResponseEntity.badRequest().body("rating point has to be more than 5");

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
        log.info("Searching for category_ebook_relation has id {} ", id);
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