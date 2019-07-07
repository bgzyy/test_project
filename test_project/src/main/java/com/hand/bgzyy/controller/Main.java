package com.hand.bgzyy.controller;

import com.hand.bgzyy.service.PageInfo;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 启动爬取历史数据程序
 * Created by zhao'yin
 * Date 2019/7/5.
 */
public class Main {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> codeListFromFile = PageInfo.getInstance().getCodeMapFromFile();
        PageInfo.getInstance().getListAndInsert(codeListFromFile);
    }
}