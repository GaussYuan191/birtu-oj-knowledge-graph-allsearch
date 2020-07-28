package com.birtu.oj.allsearch.nai.repository;


import com.birtu.oj.allsearch.nai.entry.node.Problem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: 994
 * @Date: 2020-04-30 14:49
 */
@SpringBootTest
class TitleCqlTest {

    @Autowired
    TitleCql titleCql;

    @Test
    public void getProblem(){
        String str = "硬币翻转";

        Problem problem = titleCql.findProblem(str);

        Assert.assertNotNull(problem);
    }

}