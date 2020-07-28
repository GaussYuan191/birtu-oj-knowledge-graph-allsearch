package com.birtu.oj.allsearch.nai.service;


import com.birtu.oj.allsearch.nai.Exception.Resp;
import com.birtu.oj.allsearch.nai.entry.dto.Words;
import com.birtu.oj.allsearch.nai.tengxunAPI.TengxunAPI;
import com.birtu.oj.allsearch.nai.util.JsonUtil;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * @Author: 994x
 * @Date: 2020-03-08 14:37
 */
@Service
@Log4j2
public class QAService {

    @Autowired
    private BaiDuAiService baiDuAiService;

    @Autowired
    private MethodCall methodCall;

    private TengxunAPI tengxunAPI;
    private String msgs;

    /**
     * 问答系统的方法集合处
     * @param msg
     * @return
     */
    public Resp qustion(String msg) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        //分词结果
        JSONObject jsonObject = baiDuAiService.lexicalAnalysisCustom(msg);

        //获取单词串
        Words<String,String> words = JsonUtil.getItemAndNe(jsonObject);

//        System.out.println(words);
        //找到适配方法
        Resp call = methodCall.methodCall(words,msg);

//        System.out.println(msg);
        this.msgs=msg;
        //没有查询结果
        if(call==null){
//            log.info("=====> QA 没有匹配的format");
//            return new Resp("我不太理解 (ಥ_ಥ)",null);
            tengxunAPI = new TengxunAPI();
           return tengxunAPI.QA_Tengxun(msgs);
        }

        return call;
    }

}
