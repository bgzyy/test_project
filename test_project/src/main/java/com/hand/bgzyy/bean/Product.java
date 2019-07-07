package com.hand.bgzyy.bean;

import java.sql.Date;

/**
 * Created by zhao'yin
 * Date 2019/7/4.
 */
public class Product {
    private Integer id;
    private double gp_price;
    private Date date;

    public Product(double gp_price, Date date) {
        this.gp_price = gp_price;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", gp_price=" + gp_price +
                ", date=" + date +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getGp_price() {
        return gp_price;
    }

    public void setGp_price(double gp_price) {
        this.gp_price = gp_price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}