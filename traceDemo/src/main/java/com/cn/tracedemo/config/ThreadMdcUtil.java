package com.cn.tracedemo.config;

import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @Description
 * @Author HeLiSen
 * @Date 2022/12/8 15:48
 */
public class ThreadMdcUtil {
    private static final String TRACE_ID = "TRACE_ID";

    /**
     * 获取唯一标识
     * @return
     */
    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    public static void setTraceIdIfAbsent() {
        if(MDC.get(TRACE_ID) == null) {
            MDC.put(TRACE_ID, generateTraceId());
        }
    }

    /**
     * 用于父线程向线程池中提交任务时，将自身MDC中的数据复制给子线程
     * @param callable
     * @param context
     * @return
     * @param <T>
     */
    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        //lambda表达式定义一个Callable的实现类
        return () -> {
            if(context == null) {
                MDC.clear();
            }else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            }finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        //lambda表达式定义一个Runnable的实现类
        return () -> {
            if(context == null) {
                MDC.clear();
            }else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            }finally {
                MDC.clear();
            }
        };
    }

    /*class WrapRunnable implements Runnable {

        private Map<String, String> context;
        private Runnable runnable;

        public WrapRunnable(Map<String, String> context, Runnable runnable) {
            this.context = context;
            this.runnable = runnable;
        }

        @Override
        public void run() {
            if(context == null) {
                MDC.clear();
            }else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            }finally {
                MDC.clear();
            }
        }
    }*/


}
