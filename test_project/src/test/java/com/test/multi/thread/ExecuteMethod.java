package com.test.multi.thread;

import java.time.LocalDate;

/**
 * Created by zhao'yin
 * Date 2019/7/6.
 */
public class ExecuteMethod implements Runnable {

    private String code;

    public ExecuteMethod(String code) {
        this.code = code;
    }

    /*
     * 3. 执行多线程发送过来的爬取请求，ReadTimeOut 时重新爬取
     * */
    @Override
    public void run() {
        Integer year = 2019;
        Integer jidu = 4;
        String firstUrl = "";
        String realUrl;
        firstUrl += code + ".phtml";
        LocalDate date = LocalDate.now();
        Integer monthValue = date.getMonthValue();
        int dayOfYear = date.getYear();

        for (int i = year; i >= 2009; i--) {
            if (i == dayOfYear) {
                switch (monthValue) {
                    case 1:
                    case 2:
                    case 3:
                        jidu = 1;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        jidu = 2;
                        break;
                    case 7:
                    case 8:
                    case 9:
                        jidu = 3;
                        break;
                    case 10:
                    case 11:
                    case 12:
                        jidu = 4;
                        break;
                }
            }
            for (int j = jidu; j > 0; j--) {
                String lastUrl = "?year=" + i + "&jidu=" + j;
                realUrl = firstUrl + lastUrl;
            }
            jidu = 4;
        }
    }
}