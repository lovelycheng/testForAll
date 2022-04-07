package com.example.tests;

import java.awt.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author chengtong
 * @date 2020/11/18 14:59
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

//        interruptWaitTest();
//        Thread.currentThread().stop();

//        ExecutorService s = new ThreadPoolExecutor(8,8,100, TimeUnit.SECONDS,new LinkedBlockingQueue<>());


        doubleUnParkNeedDoubleParkUseJSR166();

    }

    private static void interruptSetTest() {

        AtomicBoolean before = new AtomicBoolean(false);
        AtomicBoolean after = new AtomicBoolean(false);
        AtomicBoolean afterStaticInterrupted = new AtomicBoolean(false);
        AtomicBoolean interrupted = new AtomicBoolean(false);

        Thread t1 = new Thread(() -> {
            before.set(Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                interrupted.set(true);
            }
            after.set(Thread.currentThread().isInterrupted());//will not reset

            System.out.println(after.get());
            Thread.interrupted();

            System.out.println(Thread.currentThread().isInterrupted());



        });

        Thread t2 = new Thread(t1::interrupt);

        t1.start();
        t2.start();



    }

    private static void interruptWaitTest() throws InterruptedException {

        Object lock = new Object();

        Thread t1 = new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(()->{

            System.out.println("t1.isInterrupted");
            t1.isInterrupted();
            System.out.println("t1.interrupt");
            t1.interrupt();

            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().isInterrupted());
            Thread.interrupted();
            System.out.println(Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        t1.start();
        t2.start();


    }


    private static void doubleUnParkNeedDoublePark(){
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            long time = System.currentTimeMillis() + 10000L;
            while (System.currentTimeMillis() - time < 0){
            }

            synchronized (o){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t1.interrupt(); //唤醒一次
        System.out.println("interrupt before wait");

    }

    private static void doubleUnParkNeedDoubleParkUseJSR166() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            long time = System.currentTimeMillis() + 100L;
            while (System.currentTimeMillis() - time < 0){
            }
            LockSupport.park();
            System.out.println("should not locked one ");
            LockSupport.park();
            System.out.println("should locked two ");
        });
        t1.start();
        System.out.println("unpark 1");
        LockSupport.unpark(t1);
        Thread.sleep(10000);
        System.out.println("unpark 2");

        LockSupport.unpark(t1);

//        Thread t2 = new Thread(() -> {
//            long time = System.currentTimeMillis() + 1000L;
//            while (System.currentTimeMillis() - time < 0){
//            }
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("should not interrupt one ");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("should interrupt two ");
//        });
//        t2.start();
//        System.out.println("interrupt 1");
//        t2.interrupt();
//        LockSupport.unpark(t2);
//        Thread.sleep(10000);
//        System.out.println("interrupt 2");
//        t2.interrupt();

//        LockSupport.unpark(t2);
    }






}
