package com.cn.strategy;

/**
 * @description: label
 * @author: wangqiang
 * @create: 2020-04-21 20:19
 **/
public class JLabel {
    private Icon icon;
    private String horizontalAlignment;

    public JLabel() {
    }

    public JLabel(Icon icon, String horizontalAlignment) {
        this.icon = icon;
        this.horizontalAlignment = horizontalAlignment;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }
}
