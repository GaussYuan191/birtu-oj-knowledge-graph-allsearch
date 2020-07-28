package com.birtu.oj.allsearch.nai.service.neo;

import com.birtu.oj.allsearch.nai.entry.node.Problem;
import com.birtu.oj.allsearch.nai.repository.RecommendCql;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-04-30 13:29
 */
@SpringBootTest
class RecommendServiceTest {
    @Autowired
    RecommendService recommendService;

    @Autowired
    RecommendCql recommendCql;

    @Test
    void sameDifSameAlg() {
    }

    @Test
    void sameALgHighDif() {
    }

    @Test
    void samDifNotAlg() {
        List<String> alg = new ArrayList<>();
        alg.add("搜索");
        alg.add("排序");
        List<Problem> problemList = recommendCql.samDifNotAlg(alg, 2);
        Assert.assertNotNull(problemList);

    }
}