package com.cn.exception;

import org.springframework.util.ObjectUtils;

import java.util.concurrent.ConcurrentHashMap;

public class CodeMsgRegistry {
    private static final ConcurrentHashMap<Integer,String> REGISTRY = new ConcurrentHashMap<>(16);

    public static void addCodeMsg(Integer code,String msg){
        String old = REGISTRY.put(code,msg);
        if(old != null){
            throw new IllegalArgumentException(String.format("%d is already in use ",code));
        }
    }

    public static String getMsgByCode(Integer code,String ... arguments){
        if(ObjectUtils.isEmpty(arguments)){
            return REGISTRY.get(code);
        }
        return String.format(REGISTRY.get(code),(Object[]) arguments);
    }
}
