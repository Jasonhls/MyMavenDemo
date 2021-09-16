package com.cn.exception.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PropertySourceUtils {
    private static List<String> originBasePackages;

    public static void setBasePackages(List<String> packages){
        PropertySourceUtils.originBasePackages = packages;
    }

    public static List<String> getBasePackages(){
        return originBasePackages;
    }
}
