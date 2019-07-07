package com.test.multi.thread;

import com.hand.bgzyy.bean.AllData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhao'yin
 * Date 2019/7/6.
 */
public class MultiThreadGetInfo {

    /*
    * 1. 获取 gpCode
    * */
    public CopyOnWriteArrayList<String> getCodeFromFile() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codeList;
    }

    /*
    * 2. 遍历 codeList 拼写最终 URL，并使用多线程爬取数据
    *   2.1 遍历 codeList，将每一个 code 提交到一个新的线程执行后续逻辑
    *   2.2 每一个 code 在一个单独线程，拼写前十年每一个季度的 url 以及爬取每一个 url 中的历史数据
    *      （异步，在这里是每一个线程执行的开始，不等到该线程执行完毕下一个线程开始执行，直到有空闲的线程或所有任务执行完毕）
    * */
    public void multiThreadTask(CopyOnWriteArrayList<String> codeList) {
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        for (String code : codeList) {
            executorService.execute(new ExecuteMethod(code));
        }
    }
}