package com.example.tests;

/**
 * @author chengtong
 * @date 2020/11/20 14:24
 */
public class ObjectsTest {

    public static void main(String[] args) {

        waitNotifyTest();

    }

    static volatile boolean f = false;

    private static void waitNotifyTest(){
        Object lock = new Object();

        Thread t1 = new Thread(()->{
            synchronized (lock){
                while(!f){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("dasd");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock){
                f = true;
                lock.notify();
            }

        });


        t1.start();
        t2.start();

    }

}
