package com.cn.designMode.observe.observer;

//某一个具体的观察者
public class CurrentConditions implements Observer{
    //温度，气压，湿度
    private float temperature;
    private float pressure;
    private float humidity;

    //更新天气情况，是由WeatherData来调用，使用推送模式
    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    //显示
    public void display() {
        System.out.println("显示今天天气信息：");
        System.out.println("***Today temperature：" + temperature + "***");
        System.out.println("***Today pressure：" + pressure + "***");
        System.out.println("***Today humidity：" + humidity + "***");
    }
}
