package com.axonactive.demo.service.impl;

import com.axonactive.demo.service.CommentEbookRelationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentEbookRelationServiceImplTest {
    @Autowired
    private CommentEbookRelationService commentEbookRelationService;

    @Test
    void findCommentByEbookId_shouldReturnData_whenFound() {
        assertNotEquals(0, commentEbookRelationService.findCommentEbookRelationById(10010));
        System.out.println(commentEbookRelationService.findCommentEbookRelationById(10010).get());
    }
}