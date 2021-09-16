package com.cn.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CodeException extends RuntimeException {
    @Getter
    private int code;
    @Getter
    private String[] arguments;
    @Getter
    private HttpStatus httpStatus;

    public CodeException(String message){
        super(message);
    }

    public CodeException(int code,String message){
        super(message);
        this.code = code;
        this.arguments = new String[]{message};
    }

    public CodeException(String message, int code, HttpStatus httpStatus){
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
