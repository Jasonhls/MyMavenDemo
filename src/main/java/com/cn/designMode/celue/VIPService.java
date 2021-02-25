package com.cn.designMode.celue;

import org.springframework.stereotype.Service;

@Service
public class VIPService implements JisuanService{
    @Override
    public String tyep() {
        return "VIP";
    }

    @Override
    public double jisuan(double price) {
        return 1.5 * price;
    }
}
