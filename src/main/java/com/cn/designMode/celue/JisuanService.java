package com.cn.designMode.celue;

import org.springframework.stereotype.Service;

@Service
public interface JisuanService {
    String tyep();

    double jisuan(double price);
}
