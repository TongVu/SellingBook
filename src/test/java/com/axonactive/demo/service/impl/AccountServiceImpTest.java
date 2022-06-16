package com.axonactive.demo.service.impl;

import com.axonactive.demo.service.AccountService;
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
class AccountServiceImpTest {
    @Autowired
    AccountService accountService;

    @Test
    void findAccountByEmail_shouldReturnData_whenFound() {
        assertNotEquals(0, accountService.findAccountByEmailContaining("banhhe155@gmail.com"));
    }

    @Test
    void findAccountByPhone_shouldReturnData_whenFound() {
        assertNotEquals(0, accountService.findAccountByPhone("0914074837"));
    }
}