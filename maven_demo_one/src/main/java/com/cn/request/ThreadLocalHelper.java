package com.cn.request;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author HeLiSen
 * @Date 2022/11/10 17:42
 */
public class ThreadLocalHelper {

    private static ThreadLocal<HashMap<String, Object>> THREAD_LOCAL_MAP = ThreadLocal.withInitial(HashMap::new);

    public static <T> void put(String key, T value) {
        (THREAD_LOCAL_MAP.get()).put(key, value);
    }

    public static <T> T get(String key) {
        return (T) (THREAD_LOCAL_MAP.get()).get(key);
    }

    public static void remove(String key) {
        ((HashMap)THREAD_LOCAL_MAP.get()).remove(key);
    }

    public static void removeAll() {
        THREAD_LOCAL_MAP.remove();
    }

    public static Map<String, Object> getValues() {
        return new HashMap((Map)THREAD_LOCAL_MAP.get());
    }

    public static void setValues(Map<String, Object> valueMap) {
        (THREAD_LOCAL_MAP.get()).putAll(valueMap);
    }

    private ThreadLocalHelper() {
    }


}
