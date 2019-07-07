package com.hand.bgzyy.service;

import com.hand.bgzyy.bean.AllData;
import com.hand.bgzyy.dao.ProductMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zhao'yin
 * Date 2019/7/4.
 */
public class PageInfo {

    private ApplicationContext context;
    private ProductMapper productMapper;

    {
        context = new ClassPathXmlApplicationContext("Spring_config.xml");
        productMapper = context.getBean(ProductMapper.class);
    }

    private PageInfo() {
    }

    private static PageInfo pageInfo = null;

    public static PageInfo getInstance() {
        if (pageInfo == null) {
            synchronized (PageInfo.class) {
                if (pageInfo == null) {
                    pageInfo = new PageInfo();
                }
            }
        }
        return pageInfo;
    }

    /*
     * 从东方财富网爬取所有的股票代码，并将其包装为 List 返回
     * */
    private List<String> getCodeListFromHtmlSource() {
        ConcurrentHashMap<String, String> codeHashMap = new ConcurrentHashMap<>(3000);
        Document document;
        List<String> list = new ArrayList<>();
        try {
            document = Jsoup.connect("http://quote.eastmoney.com/stock_list.html#sz").get();
            Elements elements = document.getElementById("quotesearch").select("li");
            for (Element element : elements) {
                String code = element.text();
                if (code.startsWith("R") || code.startsWith("0") || code.startsWith("G")) {
                } else {
                    Integer index = code.indexOf("(");
                    String realCode = code.substring(index + 1, code.length() - 1);
                    String gpName = code.substring(0, index);
                    list.add(realCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * 从外部文件导入股票代码集合
     * */
    public CopyOnWriteArrayList<String> getCodeMapFromFile() {
        CopyOnWriteArrayList<String> codeList = new CopyOnWriteArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("code.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 0) {
                    Integer index = line.indexOf("(");
                    String code = line.substring(index + 1, line.length() - 1);
                    codeList.add(code);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeList;
    }

    public void getListAndInsert(CopyOnWriteArrayList<String> allCodeList) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(16);
        List<Integer> codeFromTab = productMapper.getCodeFromTab();
        CopyOnWriteArrayList<String> codeStrList = new CopyOnWriteArrayList<>();
        for (Integer code : codeFromTab) {
            codeStrList.add(code.toString());
        }
        allCodeList.removeAll(codeStrList);
        Iterator<String> iterator = allCodeList.iterator();

        while (iterator.hasNext()) {
            SpellingUrl spellingUrl = new SpellingUrl(iterator.next());
            spellingUrl.call(spellingUrl);
        }
//        Future<List<AllData>> listFuture = threadPool.submit(new SpellingUrl(iterator.next().getValue(), iterator.next().getKey()));
    }

    /*
     * 抓取新浪页面股票信息，并将单个的股票信息封装为 AllData，所有的股票信息以 List 的形式的返回
     * */
//    public List<AllData> getPageInfoFromHtmlSource(String realUrl, String gpCode) {
//        List<AllData> allDataList = new ArrayList<>();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("transfer.mogumiao.com", 9001));
//        String appKey = "U0ZYVDdVaVVwRXlFWHVLQjptcHpLb29CVXVpSGxGZGIy";
//
//        int statusCode = 0;
//        do {
//            Connection.Response response;
//            try {
//                response = Jsoup.connect(realUrl).execute();
//                statusCode = response.statusCode();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } while (statusCode == 200);
//
//
//        Document document = null;
//        String realTitle = null;
//        try {
//            document = Jsoup.connect(realUrl)
//                    //                    .ignoreContentType(true)
//                    //                    .ignoreHttpErrors(true)
//                    .timeout(10000)
//                    .proxy(proxy)
//                    .header("Proxy-Authorization", "Basic " + appKey)
//                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
//                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//                    .header("accept-encoding", "gzip, deflate, br")
//                    .header("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
//                    .get();
//            String title = document.title();
//            realTitle = title.substring(0, title.indexOf(")") + 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        document.select("table");
//        Element firstElement = document.getElementById("FundHoldSharesTable");
//        if (firstElement != null) {
//            Elements elements = firstElement.getElementsByTag("tbody").select("tr");
//            if (elements.size() != 0) {
//                elements.remove(0);
//                for (Element element : elements) {
//                    String[] line = element.getElementsByTag("tr").text().split(" ");
//                    AllData allData = new AllData(realTitle, gpCode, line[0], Double.parseDouble(line[3]));
////                productMapper.insertToInfoTab(allData);
//                    allDataList.add(allData);
//                }
//
//                for (AllData allData : allDataList) {
//                    System.out.println(allData);
//                }
//
//                productMapper.insertListToInfoTab(allDataList);
//            }
//        }
//        return allDataList;
//    }

    public List<AllData> getPageInfoFromHtmlSource(String realUrl, String gpCode) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("transfer.mogumiao.com", 9001));
        String appKey = "QjFIMmtNU0tJWndqQmhHeTpSdU9EbHAxY1k1OExYZ2Vz";
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
                    AllData allData = new AllData(realTitle, gpCode, line[0], Double.parseDouble(line[3]));
                    allDataList.add(allData);
                }

                for (AllData allData : allDataList) {
                    System.out.println(allData);
                }

                productMapper.insertListToInfoTab(allDataList);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDataList;
    }
}