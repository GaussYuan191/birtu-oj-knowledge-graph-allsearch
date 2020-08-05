package com.birtu.oj.allsearch.nai.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@Service
/**
 * @program: nai
 * @description: 调用阿里语音转文字的接口
 * @author: Gauss
 * @date: 2020-08-05 09:02
 **/
public class FileTransAli {
    public static final String APP_ID = "d50c040a3315548aee4421c312c5e176";
    public static final String PUB_KEY = "adc6b2bead0578e4fad8abd0bb252b26";
    public static final String PRI_KEY = "20daccc14e60027563eaa11f94d0658e";
    public static final String HARD_WARE_INFO = "08-94-ef-30-2a-0d";
    public static final String LICENSE_ID = "28b42298fbc841ea82d022869512cf17";
    public static final String UPLOAD_URL = "http://asr.thunisoft.net/proxy/file/upload";
    public static final String MISSION_URL = "http://asr.thunisoft.net/proxy/file/startAsr";
    public static final String TASK_URL = "http://asr.thunisoft.net/proxy/file/asrResult";

    private JSONObject authorObject = null;

    public FileTransAli() {
        this.authorObject = new JSONObject();
        this.authorObject.put("app_id", APP_ID);
        this.authorObject.put("hardwareinfo", HARD_WARE_INFO);
        this.authorObject.put("license_id", LICENSE_ID);
        this.authorObject.put("pri_key", PRI_KEY);
        this.authorObject.put("pub_key", PUB_KEY);
    }

    public String submitFileTrans(MultipartFile file) throws Exception {
        JSONObject taskObject = new JSONObject();
        taskObject.put("author", this.authorObject);

        String taskId = null;
        RestTemplate restTemplate = new RestTemplate();

        File tempFile = File.createTempFile("tmp", null);
        file.transferTo(tempFile);
        FileSystemResource resource = new FileSystemResource(tempFile);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("file", resource);

        String response = restTemplate.postForObject(UPLOAD_URL, param, String.class);
        JSONObject result = JSONObject.parseObject(response);
        tempFile.deleteOnExit();
        if (result.getString("status").equals("200")) {
            taskObject.put("audioId", result.getString("result"));
        } else {
            System.err.println(String.format("获取上传音频Id失败，失败信息%s", result.getString("message")));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(taskObject.toJSONString(), headers);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(MISSION_URL, request, JSONObject.class);
        result = JSONObject.parseObject(String.valueOf(responseEntity.getBody()));
        if (result.getString("status").equals("200")) {
            taskId = result.getString("result");
        } else {
            System.err.println(String.format("获取taskId失败，失败信息%s", result.getString("message")));
        }

        return taskId;
    }

    public String getFileTransResult(String taskId) throws Exception {
        JSONObject resultObject = new JSONObject();
        resultObject.put("author", this.authorObject);
        resultObject.put("taskId", taskId);

        String result = null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(resultObject.toJSONString(), headers);

        while (true) {
            ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(TASK_URL, request, JSONObject.class);
            JSONObject responseObject = JSONObject.parseObject(String.valueOf(responseEntity.getBody()));
            if (!responseObject.getString("status").equals("200")) {
                System.err.println(String.format("识别音频失败，失败信息%s", responseObject.getString("message")));
                break;
            }
            if (responseObject.getString("result").equals("1")) {
                Thread.sleep(3000);
            } else {
                result = responseObject.getString("data");
                break;
            }
        }

        return result;
    }
}
