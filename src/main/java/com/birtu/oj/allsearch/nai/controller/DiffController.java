package com.birtu.oj.allsearch.nai.controller;


import com.birtu.oj.allsearch.nai.Exception.Resp;
import com.birtu.oj.allsearch.nai.entry.from.DifFrom;
import com.birtu.oj.allsearch.nai.entry.from.ProblemFrom;
import com.birtu.oj.allsearch.nai.service.neo.DiffService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-07 21:52
 */
@Log4j2
@RestController
@RequestMapping("api/dif")
public class DiffController {

    @Autowired
    DiffService diffService;

    /**
     * 根据难度名称获取所有题目
     * @param from
     * @return
     */
    @PostMapping
    public Resp getByDiff(@RequestBody ProblemFrom from){
        log.info("=====> 根据难度名称获取所有题目");
        return diffService.getProblemsByDiff(from.getDiff());
    }

    /**
     * 根据难度和算法获取题目
     * @param from
     * @return
     */
    @PostMapping("/alg")
    public Resp getByDifAndAlg(@RequestBody DifFrom from){
        return diffService.getProByDifAndAlg(from);
    }
}