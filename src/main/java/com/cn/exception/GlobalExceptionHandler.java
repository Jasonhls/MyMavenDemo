package com.cn.exception;

import com.cn.exception.handlers.CodeExceptionHandler;
import com.cn.exception.handlers.DefaultCustomExcetionHandler;
import com.cn.exception.handlers.ExceptionHandlerOne;
import com.cn.exception.handlers.ExceptionHandlerTwo;
import com.cn.exception.utils.TraceIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    CustomExceptionHandlerContainer handlerContainer;

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Object handleException(HttpServletRequest request, HttpServletResponse response,
                                  Throwable e){
        ResultDTO resultDTO = handlerContainer.handleException(request, response, e);
        if(resultDTO.getCode() == null){
            resultDTO.setCode(ResultDTO.UNKOWN_CODE);
        }
        resultDTO.setReqId(TraceIdUtils.INSTANCE.getTraceId());
        if(!response.isCommitted()){
            HttpStatus status = resultDTO.getStatus();
            if(status != null){
                response.setStatus(status.value());
            }else{
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
        return resultDTO;
    }

    @Bean
    public CustomExceptionHandlerContainer customExceptionHandlerContainer(){
        return new CustomExceptionHandlerContainer();
    }

    @Bean
    public DefaultCustomExcetionHandler defaultCustomExcetionHandler(){
        return new DefaultCustomExcetionHandler();
    }

    @Bean
    public ExceptionHandlerOne exceptionHandlerOne(){
        return new ExceptionHandlerOne();
    }

    @Bean
    public ExceptionHandlerTwo exceptionHandlerTwo(){
        return new ExceptionHandlerTwo();
    }

    @Bean
    public CodeExceptionHandler codeExceptionHandler(){
        return new CodeExceptionHandler();
    }
}
