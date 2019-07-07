package com.hand.bgzyy.bean;

/**
 * Created by zhao'yin
 * Date 2019/7/5.
 */
public class GpBean {
    private String gpName;
    private String gpCode;

    public GpBean(String gpName, String gpCode) {
        this.gpName = gpName;
        this.gpCode = gpCode;
    }
    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getGpCode() {
        return gpCode;
    }

    public void setGpCode(String gpCode) {
        this.gpCode = gpCode;
    }
}