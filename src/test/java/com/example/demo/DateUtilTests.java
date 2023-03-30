package com.example.demo;


import com.example.demo.util.DateUtil;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@SpringBootTest
class DateUtilTests {

    @Test
    void shouldBeSameDateFromMillis() {
        val dateFromMillis = DateUtil.fromMillis(1680183509922L);
        val dateMocked = LocalDateTime.of(2023, 03, 30, 10, 38, 29).plus(922, ChronoUnit.MILLIS);
        Assertions.assertTrue(dateFromMillis.isEqual(dateMocked));
    }

    @Test
    void shouldBeSameMillisFromDate() {
        val millisMocked = 1680183509922L;
        val dateToParse = LocalDateTime.of(2023, 03, 30, 10, 38, 29).plus(922, ChronoUnit.MILLIS);
        val millis = DateUtil.toMillis(dateToParse);
        Assertions.assertTrue(millis == millisMocked);
    }

}