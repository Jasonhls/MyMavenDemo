package com.cn.designMode.celue;

import org.springframework.stereotype.Service;

@Service
public class SVIPService implements JisuanService{
    @Override
    public String tyep() {
        return "SVIP";
    }

    @Override
    public double jisuan(double price) {
        return 2.5 * price;
    }
}
