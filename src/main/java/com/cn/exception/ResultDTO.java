package com.cn.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
public class ResultDTO<T> implements Serializable {
    private HttpStatus status;
    private String reqId;
    private String messgae;
    private Integer code;
    private T data;

    public static final Integer UNKOWN_CODE = -1;
    public static final Integer SUCCESS_CODE = 0;

    public static ResultDTO fail(int code,String msg){
        return ResultDTO.builder().code(code).messgae(msg).build();
    }
}
