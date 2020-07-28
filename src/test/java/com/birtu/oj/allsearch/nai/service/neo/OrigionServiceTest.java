package com.birtu.oj.allsearch.nai.service.neo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: 994
 * @Date: 2020-03-21 21:17
 */
@SpringBootTest
class OrigionServiceTest {
    @Autowired
    RegionService regionService;

    String region1 = "湖南";
    String region2 = "四川";
    String time1 = "2010";

    @Test
    void getByOriTest() {
    }

    @Test
    void getByOriAndTime() {
    }
}