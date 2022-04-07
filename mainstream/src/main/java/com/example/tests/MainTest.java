package com.example.tests;

import com.example.tests.impl.CacheFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengtong
 * @date 2020/6/17 23:54
 */
public class MainTest {

    public static void main(String[] args) {
//        Cache cache = CacheFactory.build(new Config());

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10,15,0L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        poolExecutor.execute(runnable);

    }

}
