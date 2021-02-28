package com.cn.designMode.observe;

import com.cn.designMode.observe.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 类是核心
 * 1.包含最新的天气情况
 * 2.含有观察者集合，使用ArrayList管理
 * 3.当数据更新时，就主动调用ArrayList，通知所有的（接入方）就看到最新的信息
 */
public class WeatherData implements Subject{

    //温度，气压，湿度
    private float temperature;
    private float pressure;
    private float humidity;


    //观察者集合
    private List<Observer> observers;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    //设置最新的天气信息
    public void setData(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        //调用数据更改方法
        dataChange();
    }

    public void dataChange() {
        //通知所有的观察者
        notifyObservers();
    }


    //注册一个观察者
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    //移除一个观察者
    @Override
    public void removeObserver(Observer observer) {
        if(observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    //遍历所有的观察者，并通知
    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(this.temperature, this.pressure, this.humidity);
        }
    }


    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
