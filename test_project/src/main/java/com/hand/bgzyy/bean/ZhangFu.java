package com.hand.bgzyy.bean;

/**
 * Created by zhao'yin
 * Date 2019/7/7.
 */
public class ZhangFu {
    private String gpName;
    private String date;
    private double zf;

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getZf() {
        return zf;
    }

    public void setZf(double zf) {
        this.zf = zf;
    }

    public ZhangFu(String gpName, String date, double zf) {
        this.gpName = gpName;
        this.date = date;
        this.zf = zf;
    }
}