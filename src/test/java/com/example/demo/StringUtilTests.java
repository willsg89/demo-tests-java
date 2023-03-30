package com.example.demo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.demo.util.StringUtil.isEmpty;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class StringUtilTests {

    @Test
    void shouldBeFalseWithNormalText() {
        assertFalse(isEmpty("tets"));
    }

    @Test
    void shouldBeTrueWithEmptySpace() {
        assertTrue(isEmpty("  "));
    }

    @Test
    void shouldBeTrueWithNull() {
        assertTrue(isEmpty(null));
    }

}