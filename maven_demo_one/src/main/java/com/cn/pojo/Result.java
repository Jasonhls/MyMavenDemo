package com.cn.pojo;

/**
 * Created by Jason on 2018/10/18.
 */
public class Result {
    private boolean state;//true表示成功，false表示失败
    private String message;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
