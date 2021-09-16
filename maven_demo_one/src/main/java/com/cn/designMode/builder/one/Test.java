package com.cn.designMode.builder.one;

public class Test {
    public static void main(String[] args) {
        //盖普通的房子
        CommonHouse commonHouse = new CommonHouse();
        //准备创建房子的指挥者
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        //完成盖房子，返回产品（普通房子）
        houseDirector.constructHose();

        System.out.println("-----------------");

        //盖高楼
        HighHouse highHouse = new HighHouse();
        //重置建造者
        houseDirector.setHouseBuilder(highHouse);
        //完成盖房子，返回产品（高楼）
        houseDirector.constructHose();
    }
}
