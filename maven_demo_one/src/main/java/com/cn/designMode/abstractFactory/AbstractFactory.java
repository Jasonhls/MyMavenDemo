package com.cn.designMode.abstractFactory;

import com.cn.designMode.abstractFactory.pizza.Pizza;

public interface AbstractFactory {
    Pizza createPizza(String orderType);
}
