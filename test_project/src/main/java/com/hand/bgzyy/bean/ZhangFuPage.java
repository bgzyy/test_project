package com.hand.bgzyy.bean;

/**
 * Created by zhao'yin
 * Date 2019/7/8.
 */
public class ZhangFuPage {
    private String name;
    private Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ZhangFuPage(String name, Integer count) {
        this.name = name;
        this.count = count;
    }
}
