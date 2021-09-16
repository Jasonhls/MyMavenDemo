package com.cn.exception.handlers;

import com.cn.exception.CustomExceptionHandler;
import com.cn.exception.ResultDTO;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class DefaultCustomExcetionHandler implements CustomExceptionHandler {
    @Override
    public Optional<ResultDTO> handleException(HttpServletRequest request, HttpServletResponse response, Throwable e, Throwable re) {
        ResultDTO res;
        if(e instanceof HttpRequestMethodNotSupportedException){
            HttpRequestMethodNotSupportedException ee = (HttpRequestMethodNotSupportedException) e;
            String[] supportedHttpMethods = ee.getSupportedMethods();
            if(supportedHttpMethods != null){
                response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedHttpMethods,", "));
            }
            res = ResultDTO.builder().status(HttpStatus.METHOD_NOT_ALLOWED).messgae(
                    String.format("不支持%s请求方式",((HttpRequestMethodNotSupportedException) e).getMethod())).build();
        }else if(e instanceof HttpMediaTypeNotSupportedException){
            List<MediaType> mediaTypes = ((HttpMediaTypeNotSupportedException) e).getSupportedMediaTypes();
            if(!CollectionUtils.isEmpty(mediaTypes)){
                response.setHeader("Accept", MediaType.toString(mediaTypes));
            }
            res = ResultDTO.builder().status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).messgae(
                    String.format("不支持MediaType%s",request.getContentType())).build();
        }else if(e instanceof HttpMediaTypeNotAcceptableException){
            res = ResultDTO.builder().status(HttpStatus.NOT_ACCEPTABLE).messgae(e.getMessage()).build();
        }else if(e instanceof MissingPathVariableException || e instanceof ConversionNotSupportedException){
            res = ResultDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).messgae(e.getMessage()).build();
        }else if(e instanceof MissingServletRequestParameterException
        || e instanceof ServletRequestBindingException
        || e instanceof HttpMessageNotReadableException
        || e instanceof HttpMessageNotWritableException
        || e instanceof MissingServletRequestPartException
        || e instanceof TypeMismatchException){
            res = ResultDTO.builder().status(HttpStatus.BAD_REQUEST).messgae(e.getMessage()).build();
        }else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException)e;
            BindingResult bindingResult = exception.getBindingResult();
            res = processBindingResult(bindingResult);
        }else if(e instanceof NoHandlerFoundException){
            res = ResultDTO.builder().status(HttpStatus.NOT_FOUND).messgae(e.getMessage()).build();
        }else if(e instanceof AsyncRequestTimeoutException){
            res = ResultDTO.builder().status(HttpStatus.SERVICE_UNAVAILABLE).messgae("请求超时，请稍后重试").build();
        }else if(e instanceof IllegalArgumentException){
            res = ResultDTO.builder().status(HttpStatus.BAD_REQUEST).messgae("请求参数不正确").build();
        }else{
            return Optional.empty();
        }
        return Optional.of(res);
    }

    private ResultDTO processBindingResult(BindingResult bindingResult){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        for (ObjectError allError : allErrors){
            if(sb.length() > 0){
                sb.append("|");
            }
            if(allError instanceof FieldError){
                FieldError error = (FieldError)allError;
                sb.append(error.getField()).append(":");
            }
            sb.append(allError.getDefaultMessage());
        }
        return ResultDTO.builder().status(HttpStatus.BAD_REQUEST).messgae(sb.toString()).build();
    }
}
