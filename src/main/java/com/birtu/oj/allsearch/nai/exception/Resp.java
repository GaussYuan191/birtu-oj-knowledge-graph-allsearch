package com.birtu.oj.allsearch.nai.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @program: birtu-oj-knowledge-graph-allsearch
 * @description: 返回数据格式
 * @author: Gauss
 * @date: 2020-07-26 13:41
 **/

@Data
public class Resp {

    /**
     * 状态码
     */
    @JsonProperty(value = "code")
    private int code;

    /**
     * 信息说明
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * 数据体
     */
    @JsonProperty(value = "data")
    private Object data;

    /**
     * http状态
     */
    @JsonProperty(value = "status")
    private HttpStatus httpStatus;

    public Resp(int code,String message){
        this.code = code;
        this.message = message;
        this.data = null;
        this.httpStatus = ErrorCode.SUCCESS.getHttpStatus();
    }

    public Resp(ErrorCode errorCode, Object data){
        this.code = errorCode.getReasonCode();
        this.message = errorCode.getReasonPhrase();
        this.httpStatus = errorCode.getHttpStatus();
        this.data = data;
    }

    /**
     * 默认为code：200 成功格式
     * @param data
     */
    public Resp(Object data){
        this.code = ErrorCode.SUCCESS.getReasonCode();
        this.message = ErrorCode.SUCCESS.getReasonPhrase();
        this.httpStatus = ErrorCode.SUCCESS.getHttpStatus();
        this.data = data;
    }

    public Resp(String message,Object data){
        this.code = ErrorCode.SUCCESS.getReasonCode();
        this.httpStatus = ErrorCode.SUCCESS.getHttpStatus();
        this.message = message;
        this.data = data;
    }

}

