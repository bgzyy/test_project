package com.hand.bgzyy.service;

import com.hand.bgzyy.bean.AllData;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/5.
 */
public class SpellingUrl {

    private String gpCode;

    public SpellingUrl(String gpCode) {
        this.gpCode = gpCode;
    }

    public List<AllData> call(SpellingUrl spellingUrl) {
        String firstUrl = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/";
        firstUrl += gpCode + ".phtml";
        List<AllData> allDataList = null;

        Integer year = 2019;
        Integer jidu = 4;
        LocalDate date = LocalDate.now();
        Integer monthValue = date.getMonthValue();
        int dayOfYear = date.getYear();
        String realUrl;

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
                allDataList = PageInfo.getInstance().getPageInfoFromHtmlSource(realUrl, gpCode);
            }
            jidu = 4;
        }
        return allDataList;
    }
}