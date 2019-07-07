package com.test.source;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhao'yin
 * Date 2019/7/4.
 */
public class GetAllGpCode {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ConcurrentHashMap<String, String> codeHashMap = new ConcurrentHashMap<>(2000);
        Document document;
//        List<String> list = new ArrayList<>();
        try {
            document = Jsoup.connect("http://quote.eastmoney.com/stock_list.html#sz").get();
            Elements elements = document.getElementById("quotesearch").select("li");
            for (Element element : elements) {
                String code = element.text();
                if (code.startsWith("R") || code.startsWith("0") || code.startsWith("G")) { }
                else {
                    Integer index = code.indexOf("(");
                    String realCode = code.substring(index + 1, code.length() - 1);
                    String gpName = code.substring(0, index);
                    codeHashMap.put(gpName, realCode);
//                    list.add(realCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<Map.Entry<String, String>> iterator = codeHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getKey() + ": " + iterator.next().getValue());
        }
        System.out.println(codeHashMap.size());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}