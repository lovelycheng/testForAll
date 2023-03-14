package com.example.tests;

import java.io.IOException;
import java.util.Collections;
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
        //
        // ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 8, 0, TimeUnit.MINUTES,
        //     new LinkedBlockingQueue());
        //
        // CompletableFuture<Object> de =CompletableFuture.supplyAsync(() -> {
        //     try {
        //         Thread.sleep(10000);
        //         System.out.println("sleep end");
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        //     return new Object();
        // });
        //
        // de.thenAccept(System.err::println);
        // Thread.sleep(10000);
        // String s = "sda";
        char c = 'G';
        String ccc = c+"";
        if(ccc.matches("[0-9A-Fa-f]")){
            System.err.println(ccc);
        }
        String str = Integer.toBinaryString(c);
        StringBuilder sb = new StringBuilder(str);
        Integer s = Integer.parseInt(sb.reverse().toString(),2);
        String s1 = Integer.toHexString(s);
    }




}
