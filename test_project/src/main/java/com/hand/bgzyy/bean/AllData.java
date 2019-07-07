package com.hand.bgzyy.bean;

import java.sql.Date;

/**
 * Created by zhao'yin
 * Date 2019/7/5.
 */
public class AllData {
    private String gpName;
    private String gpCode;
    private String date;
    private Double price;

    @Override
    public String toString() {
        return "AllData{" +
                "gpName='" + gpName + '\'' +
                ", gpCode='" + gpCode + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                '}';
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public AllData(String gpName, String gpCode, String date, Double price) {
        this.gpName = gpName;
        this.gpCode = gpCode;
        this.date = date;
        this.price = price;
    }
}