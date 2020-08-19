package com.birtu.oj.allsearch.nai.controller;


import com.birtu.oj.allsearch.nai.Exception.Resp;
import com.birtu.oj.allsearch.nai.service.FileTransAli;
import com.birtu.oj.allsearch.nai.service.QAService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * @program: nai
 * @description: 语音转文字控制器
 * @author: Gauss
 * @date: 2020-08-05 09:01
 **/
@Controller
public class VideoController {
    @Autowired
    private FileTransAli fileTransAli;

    @Autowired
    QAService qaService;

    @RequestMapping(value = "/")
    public String webPlatformIndex() {
        return "index";
    }

    @RequestMapping("/aliSubmit")
    @ResponseBody
    public Resp fileTransRequest(@RequestParam("file") MultipartFile file) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String result = null;
        try {
            String taskId = fileTransAli.submitFileTrans(file);
            result = fileTransAli.getFileTransResult(taskId);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();

        }
        if(!result.equals("[]")){
            //        System.out.println(result);
            //去toString方法中产生的括号
            String b = result.substring(0,result.length() -1);
            String c = b.substring(1,b.length());
//        System.out.println(b + "b__");
            JSONObject jsonObject = JSONObject.fromObject(c);
            String text = jsonObject.getString("text");
            //text 为语音识别的内容
            System.out.println(text);

            Resp res = qaService.qustion(text);
            res.setText(text);
            System.out.println(res.toString());
            return res;
        }
        else{

            return new Resp("没听清你在说什么");
        }




    }
}
