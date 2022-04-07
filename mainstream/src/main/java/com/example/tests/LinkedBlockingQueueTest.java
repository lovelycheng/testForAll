package com.example.tests;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chengtong
 * @date 2020/7/10 14:28
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue a = new LinkedBlockingQueue();

        a.add("dasdasd");
        a.add("dasdasd1");
        a.add("dasdasd2");

        Object o = a.take();

        LinkedBlockingDeque b = new LinkedBlockingDeque();

        b.add("dasdasd");
        b.add("dasdasd1");
        b.add("dasdasd2");

        o = b.take();

    }


}
