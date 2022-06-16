package com.axonactive.demo.service.impl;

import com.axonactive.demo.service.EbookService;
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
class EbookServiceImplTest {
    @Autowired
    EbookService ebookService;

    @Test
    void findEbookByPagesGreaterThan_shouldReturnData_whenFound() {
        assertNotEquals(0, ebookService.findEbookByPagesGreaterThan(600));
    }
}