package com.example.demo;


import com.example.demo.service.UserService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void shouldBeEmptyDatabase() {
        val users = userService.findAll();
        assertTrue(users.isEmpty());
    }

    @Test
    void shouldBeThrowAnErrorNotFound() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.findByName("joao");
        });
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void shouldBeThrowAnErrorIfEmpty() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.insert("  ");
        });
        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception.getMessage().equals("name.empty"));
    }

    @Test
    void shouldBeThrowAnErrorIfNull() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.insert(null);
        });
        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception.getMessage().equals("name.empty"));
    }

    @Test
    void shouldBeInsertAnUser() {
        val user =  userService.insert("joao");
        assertTrue(!isNull(user.getId()));
        val userFind = userService.findByName("joao");
        assertTrue(userFind.getId().equals(user.getId()));
    }

}