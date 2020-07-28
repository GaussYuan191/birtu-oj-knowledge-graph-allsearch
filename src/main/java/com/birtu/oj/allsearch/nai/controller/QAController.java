package com.birtu.oj.allsearch.nai.controller;


import com.birtu.oj.allsearch.nai.Exception.Resp;
import com.birtu.oj.allsearch.nai.entry.from.QAFrom;
import com.birtu.oj.allsearch.nai.service.QAService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 问答接口
 * @Author: 994
 * @Date: 2020-03-08 14:36
 */
@RequestMapping("/api/qa")
@RestController
@Log4j2
public class QAController {

    @Autowired
    QAService qaService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public Resp question(QAFrom qaFrom) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        if(qaFrom.getMsg() == null || "".equals(qaFrom.getMsg())){
            return new Resp(200,"请输入查询语句");
        }
        log.info("=====> 提问内容 : " + qaFrom.getMsg());

        Resp res = qaService.qustion(qaFrom.getMsg());

        log.debug("=====> 问答完成");
        //存在查询结果
        return res;
    }

}
