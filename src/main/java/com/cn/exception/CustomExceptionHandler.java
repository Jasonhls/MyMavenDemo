package com.cn.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface CustomExceptionHandler {
    Optional<ResultDTO> handleException(HttpServletRequest request, HttpServletResponse response,
                                        Throwable e, Throwable re);
}
