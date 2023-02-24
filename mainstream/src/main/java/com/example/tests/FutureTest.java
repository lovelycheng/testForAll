package com.example.tests;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengtong
 * @date 2023/2/24 13:48
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 8, 0, TimeUnit.MINUTES,
            new LinkedBlockingQueue());

        CompletableFuture<Object> de =CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("sleep end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Object();
        });

        de.thenAccept(System.err::println);
        Thread.sleep(10000);
    }




}
