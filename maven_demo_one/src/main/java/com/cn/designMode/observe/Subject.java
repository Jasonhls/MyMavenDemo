package com.cn.designMode.observe;

import com.cn.designMode.observe.observer.Observer;

//接口，让WeatherData来实现
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
