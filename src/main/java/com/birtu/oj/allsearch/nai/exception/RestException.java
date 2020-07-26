package com.birtu.oj.allsearch.nai.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @program: birtu-oj-knowledge-graph-allsearch
 * @description: 返回rest格式的异常定义。
 * @author: Gauss
 * @date: 2020-07-26 13:41
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class RestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;
    private final int reasonCode;
    private Object data;

    public RestException(ErrorCode errorCode) {
        super(errorCode.getReasonPhrase());
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getHttpStatus();
        this.reasonCode = errorCode.getReasonCode();
    }

    public RestException(ErrorCode errorCode,Object data) {
        super(errorCode.getReasonPhrase());
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getHttpStatus();
        this.reasonCode = errorCode.getReasonCode();
        this.data = data;
    }

    public RestException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getHttpStatus();
        this.reasonCode = errorCode.getReasonCode();
    }
}
