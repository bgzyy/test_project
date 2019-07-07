package com.test.multi.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhao'yin
 * Date 2019/7/5.
 */
class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("hello world");
    }
}
public class MainTest {
    public static void main(String[] args) {
        //创建定时器对象
        Timer t=new Timer();
        //在3秒后执行MyTask类中的run方法,后面每10秒跑一次
        t.schedule(new MyTask(), 3000,10000);
    }
}