package com.hand.bgzyy.bean;

/**
 * Created by zhao'yin
 * Date 2019/7/7.
 */
public class ZhangFu {
    private AllData allData;
    private double zf;
    private Integer allNum;

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public ZhangFu(AllData allData, double zf) {
        this.allData = allData;
        this.zf = zf;
    }

    public AllData getAllData() {
        return allData;
    }

    public void setAllData(AllData allData) {
        this.allData = allData;
    }

    public double getZf() {
        return zf;
    }

    public void setZf(double zf) {
        this.zf = zf;
    }
}