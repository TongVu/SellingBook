package com.axonactive.demo.service.impl;

import com.axonactive.demo.entity.CategoryEbookRelation;
import com.axonactive.demo.service.CategoryEbookRelationService;
import com.axonactive.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CategoryEbookRelationServiceImplTest {
    @Autowired
    CategoryEbookRelationService categoryEbookRelationService;

    @Test
    void findEbookByRating_shouldReturnEbooksHaveRatingLargerThanRatingPoints_whenFound() {
        double RATING_POINTS = 4.0;
        List<CategoryEbookRelationDto> results = categoryEbookRelationService.findEbookByRating(RATING_POINTS);
        assertNotEquals(0, results.size());
        assertEquals(4, results.get(4).getRating());
    }

    @Test
    void findEbookByCategoryName_shouldReturnData_whenFound() {
        List<CategoryEbookRelation>  results = categoryEbookRelationService.findEbookByCategoryNameIgnoreCase("FANTASY");
        assertNotEquals(0, results.size());
    }

    @Test
    void findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan() {
        List<CategoryEbookRelation> results = categoryEbookRelationService
                .findEbookByCategoryNameIgnoreCaseAndEbookRatingGreaterThan("fantasy", 3.2);

        assertNotEquals(0, results.size());
    }
}