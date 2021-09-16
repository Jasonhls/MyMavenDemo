package com.cn.exception.handlers;

import com.cn.exception.CustomExceptionHandler;
import com.cn.exception.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.PriorityOrdered;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
public class ExceptionHandlerTwo implements CustomExceptionHandler, PriorityOrdered {
    @Override
    public Optional<ResultDTO> handleException(HttpServletRequest request, HttpServletResponse response, Throwable e, Throwable re) {
        log.info("经过了异常处理two");
        return Optional.empty();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
