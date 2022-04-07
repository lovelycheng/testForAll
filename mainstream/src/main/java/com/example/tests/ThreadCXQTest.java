package com.example.tests;

import java.io.IOException;

/**
 * @author chengtong
 * @date 2020/11/18 17:33
 */
public class ThreadCXQTest {

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            synchronized (lock){
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("thread-2 start");
            synchronized (lock){
                System.out.println("thread-2 enter");
            }
            System.out.println("thread-2 end");
        });
        Thread t3 = new Thread(() -> {
            System.out.println("thread-3 start");
            synchronized (lock){
                System.out.println("thread-3 enter");
            }
            System.out.println("thread-3 end");
        });

        Thread t4 = new Thread(() -> {
            System.out.println("thread-4 start");
            synchronized (lock){
                System.out.println("thread-4 enter");
            }
            System.out.println("thread-4 end");
        });

        t1.start();

        t2.start();
        Thread.sleep(100);
        t3.start();
        Thread.sleep(100);
        t4.start();
    }


}
