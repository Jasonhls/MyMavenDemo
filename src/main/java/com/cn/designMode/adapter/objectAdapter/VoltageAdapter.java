package com.cn.designMode.adapter.objectAdapter;

//适配器类
public class VoltageAdapter implements IVoltage5V {

    private Voltage220V voltage220V;//关联关系-聚合

    //通过构造器，传入一个Voltage220V实例
    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int dstV = 0;
        if(voltage220V != null) {
            //获取220V电压
            int srcV = voltage220V.output220V();
            dstV = srcV / 44;
            System.out.println("适配完成，输出电压为：" + dstV);
        }
        return dstV;
    }
}
