package com.cn.designMode.observe;

import com.cn.designMode.observe.observer.BaiduSiteObserver;
import com.cn.designMode.observe.observer.CurrentConditions;

public class Client {
    public static void main(String[] args) {
        //创建一个WeatherData
        WeatherData weatherData = new WeatherData();

        //创建观察者
        CurrentConditions currentConditions = new CurrentConditions();
        BaiduSiteObserver baiduSiteObserver = new BaiduSiteObserver();

        //注册观察者到WeatherData
        weatherData.registerObserver(currentConditions);
        weatherData.registerObserver(baiduSiteObserver);

        //测试
        weatherData.setData(10f, 100f, 30f);

        System.out.println("移除某一个具体的观察者");
        weatherData.removeObserver(baiduSiteObserver);
        weatherData.setData(20f, 120f, 25);

    }
}
