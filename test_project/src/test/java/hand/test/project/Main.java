package hand.test.project;

import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by zhao'yin
 * Date 2019/7/7.
 */
public class Main {
    @Test
    public void connectWeb() {
        HttpURLConnection httpURLConnection;
        boolean flag = true;
        Document document = null;
        while (flag) {
            try {
                URL url = new URL("http://money.finance.sina.com.cn/q/view/newFLJK.php?param=area");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(8000);
                document = Jsoup.parse(httpURLConnection.getInputStream(), "gb2312", "http://money.finance.sina.com.cn/q/view/newFLJK.php?param=area");
                flag = false;
            } catch (IOException e) {
                System.out.println("连接失败，重新连接中！");
            }
        }
        String docStr = document.toString();
        int begin = docStr.indexOf("{");
        int end = docStr.indexOf("}");
        String jsonStr = docStr.substring(begin, end + 1);
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        Iterator iterator = jsonObject.entrySet().iterator();
    }
}