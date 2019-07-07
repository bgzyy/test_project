package com.test.source;

/**
 * Created by zhao'yin
 * Date 2019/7/4.
 */
public class Product {

    private String gp_name;
    private String gp_price;
    private String date;

    public Product(String gp_name, String gp_price, String date) {
        this.gp_name = gp_name;
        this.gp_price = gp_price;
        this.date = date;
    }

    public String getGp_name() {
        return gp_name;
    }

    public void setGp_name(String gp_name) {
        this.gp_name = gp_name;
    }

    public String getGp_price() {
        return gp_price;
    }

    public void setGp_price(String gp_price) {
        this.gp_price = gp_price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "gp_name='" + gp_name + '\'' +
                ", gp_price='" + gp_price + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}