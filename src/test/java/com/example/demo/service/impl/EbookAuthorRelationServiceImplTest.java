package com.example.demo.service.impl;

import com.example.demo.service.EbookAuthorRelationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EbookAuthorRelationServiceImplTest {
    @Autowired
    EbookAuthorRelationService ebookAuthorRelationService;

    @Test
    void getAll_shouldReturnData_whenFound() {
        assertNotEquals(0, ebookAuthorRelationService.getAll().size());
    }

//    @Test
//    void save() {
//    }
//
//    @Test
//    void deleteById() {
//    }

    @Test
    void findEbookAuthorRelationById() {
        assertEquals(1, ebookAuthorRelationService.findEbookAuthorRelationById(1).get().getId());
    }

    @Test
    void findByAuthorLastNameContainingIgnoreCase() {
        assertNotEquals(0, ebookAuthorRelationService.findByAuthorLastNameContainingIgnoreCase("murakami"));
    }

    @Test
    void findEbooksByPublisher() {
        assertNotEquals(0, ebookAuthorRelationService.findEbooksByPublisher("Stairway Press"));
    }
}