package com.test.source;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by zhao'yin
 * Date 2019/7/5.
 */
public class TestProxy {
    public static void main(String args[]) throws Exception {
        // 要访问的目标页面
        // String targetUrl = "http://www.baidu.com";
        String targetUrl = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600000.phtml";

        // 代理服务器
        String proxyServer = "transfer.mogumiao.com";
        int proxyPort = 9001;

        // 隧道代理订单appKey(请注意替换)
        String appKey = "VjRmV203MTR0Vzc3T0VndDpVbGV0bFRiRUdGSEZZU0ZC";

        try {
            URL url = new URL(targetUrl);
            // 创建代理服务器地址对象
            InetSocketAddress addr = new InetSocketAddress(proxyServer, proxyPort);
            // 创建HTTP类型代理对象
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

            // 设置通过代理访问目标页面
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            // 设置协议头 (下面的必带，其他的可以自定义，根据自己的需求决定)
            connection.setRequestProperty("Proxy-Authorization", "Basic " + appKey);
            // 解析返回数据
            byte[] response = readStream(connection.getInputStream());

            System.out.println(new String(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将输入流转换成字符串
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;

        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();

        return outSteam.toByteArray();
    }
}