package com.test.source;

import com.hand.bgzyy.bean.AllData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao'yin
 * Date 2019/7/4.
 */
public class TestJsoup {
    public static void main(String[] args) {
        String firstUrl = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600571.phtml";
        getRealUrl(firstUrl);
    }

    public static void getRealUrl(String firstUrl) {
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
                getUrlInfo(realUrl);
            }
            jidu = 4;
        }
    }

    public static List<AllData> getUrlInfo(String realUrl) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("transfer.mogumiao.com", 9001));
        String appKey = "U0ZYVDdVaVVwRXlFWHVLQjptcHpLb29CVXVpSGxGZGIy";
        List<AllData> allDataList = new ArrayList<>();
        try {
            Document document;
            document = Jsoup.connect(realUrl)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .timeout(10000)
                    .proxy(proxy)
                    .header("Proxy-Authorization", "Basic " + appKey)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
//                    .userAgent(ua[i])
                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("accept-encoding", "gzip, deflate, br")
                    .header("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                    .get();
            String title = document.title();
            String realTitle = title.substring(0, title.indexOf(")") + 1);
            document.select("table");
            Element firstElement = document.getElementById("FundHoldSharesTable");
            if (firstElement != null) {
                Elements elements = firstElement.getElementsByTag("tbody").select("tr");
                elements.remove(0);
                for (Element element : elements) {
                    String[] line = element.getElementsByTag("tr").text().split(" ");
                    AllData allData = new AllData(realTitle, "600000", line[0], Double.parseDouble(line[3]));
                    allDataList.add(allData);
                }

                for (AllData allData : allDataList) {
                    System.out.println(allData);
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDataList;
    }
}