package com.cn.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: helisen
 * @create: 2021-05-07 16:53
 **/
public class TestOOM {
    public static class OOMObject {
        private byte[] bytes = new byte[1024 * 1024];
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while(true) {
            list.add(new OOMObject());
        }
    }
}
