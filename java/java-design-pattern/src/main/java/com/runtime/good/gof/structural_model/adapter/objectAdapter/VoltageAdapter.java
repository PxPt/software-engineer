package com.runtime.good.gof.structural_model.adapter.objectAdapter;

//适配器类
public class VoltageAdapter implements Voltage5V {
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        //获取到220V电压
        int srcV=voltage220V.output220V();
        int dstV=srcV/44;//转成5V
        return dstV;
    }
}