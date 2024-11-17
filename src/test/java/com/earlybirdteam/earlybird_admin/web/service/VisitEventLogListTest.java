package com.earlybirdteam.earlybird_admin.web.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class VisitEventLogListTest {

    @DisplayName("")
    @Test
    void test() {
        // given
        LocalDateTime createdAt = LocalDateTime.of(2024,11,2,0,0,0);
        LocalDateTime day0 = LocalDateTime.of(2024, 11, 1, 0, 0, 0);

        System.out.println(ChronoUnit.DAYS.between(day0, createdAt));
        // when


        // then


    }

}