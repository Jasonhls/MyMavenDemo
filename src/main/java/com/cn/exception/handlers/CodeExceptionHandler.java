package com.cn.exception.handlers;

import com.cn.exception.CodeException;
import com.cn.exception.CodeMsgRegistry;
import com.cn.exception.CustomExceptionHandler;
import com.cn.exception.ResultDTO;
import org.springframework.core.PriorityOrdered;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CodeExceptionHandler implements CustomExceptionHandler, PriorityOrdered {
    @Override
    public Optional<ResultDTO> handleException(HttpServletRequest request, HttpServletResponse response, Throwable e, Throwable re) {
        if(e instanceof CodeException || re instanceof CodeException){
            CodeException ce = (CodeException)(e instanceof CodeException ? e : re);
            int code = ce.getCode();
            String[] arguments = ce.getArguments();
            ResultDTO resultDTO;
            if(ce.getHttpStatus() != null){
                resultDTO = ResultDTO.fail(code,ce.getMessage());
                resultDTO.setStatus(ce.getHttpStatus());
            }else{
                resultDTO = ResultDTO.fail(code, CodeMsgRegistry.getMsgByCode(code,arguments));
                if(code < ResultDTO.SUCCESS_CODE){
                    resultDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    resultDTO.setStatus(HttpStatus.BAD_REQUEST);
                }
            }
            return Optional.of(resultDTO);
        }
        return Optional.empty();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
