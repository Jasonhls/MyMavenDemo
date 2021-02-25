package com.cn.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.http.HttpStatus;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CustomExceptionHandlerContainer {
    @Autowired
    private List<CustomExceptionHandler> handlers;


    public ResultDTO handleException(HttpServletRequest request, HttpServletResponse response,
                                     Throwable e){
        ResultDTO resultDTO = null;
        Throwable re = NestedExceptionUtils.getMostSpecificCause(e);
        for (CustomExceptionHandler handler : handlers){
            Optional<ResultDTO> resp = handler.handleException(request, response, e, re);
            if(resp.isPresent()){
                resultDTO = resp.get();
                break;
            }
        }
        if(resultDTO == null){
            resultDTO = ResultDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).messgae("服务器异常，请稍后再试").build();
        }
        if(e instanceof CodeException){
            CodeException codeException = (CodeException)e;
            log.warn("business error. code:{} ,msg:{}",codeException.getCode(),CodeMsgRegistry.getMsgByCode(codeException.getCode(),
                    codeException.getArguments()));
        }else{
            log.error("error occurred",e);
        }
        return resultDTO;
    }

    @PostConstruct
    public void init(){
        AnnotationAwareOrderComparator.sort(handlers);
    }
}
