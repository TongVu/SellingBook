package com.example.demo.service.impl;

import com.example.demo.service.CategoryEbookRelationService;
import com.example.demo.service.dto.categoryEbookRelationDto.CategoryEbookRelationDto;
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
        Integer RATING_POINTS = 4;
        List<CategoryEbookRelationDto> results = categoryEbookRelationService.findEbookByRating(RATING_POINTS);
        assertNotEquals(0, results.size());
        assertEquals(4, results.get(4).getRating());
    }
}