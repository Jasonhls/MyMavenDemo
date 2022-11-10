package com.cn.http;

import reactor.netty.http.client.HttpClient;

/**
 * @Description
 * @Author HeLiSen
 * @Date 2022/10/11 18:03
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        HttpClient client = HttpClient.create();

        String s = client.get()
                .uri("/hello")
                .responseContent()
                .aggregate()
                .asString()
                .block();
        System.out.println(s);
    }
}
