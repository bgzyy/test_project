package com.test.multi.thread;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by zhao'yin
 * Date 2019/7/5.
 */
public class ThreadTest {
    @Test
    public void test() {
        List<Future> futureList = new ArrayList<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 40; i++) {
            hashMap.put(Integer.toString(i), Integer.toString(i));
        }

        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Future<String> future = threadPool.submit(new CallableTest(iterator.next().getKey()));
            futureList.add(future);
        }

        for (Future str : futureList) {
            try {
                System.out.println(str.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class CallableTest implements Callable<String> {

    private String name;

    public CallableTest(String name) {
        this.name = name;
    }

    @Override
    public String call() {
        return Thread.currentThread().getName() + " -> " + name;
    }
}