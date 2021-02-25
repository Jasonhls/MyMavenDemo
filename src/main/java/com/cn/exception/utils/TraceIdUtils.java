package com.cn.exception.utils;

import org.slf4j.MDC;

public enum TraceIdUtils {
    INSTANCE;

    public String getTraceId(){
        String id = MDC.get("traceId");
        return id != null && id.length() > 0 ? id : null;
    }
}
