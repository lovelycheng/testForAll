package com.example.tests.argl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengtong
 * @date 2020/11/16 16:00
 */
public class LBQTest {


    public static void main(String[] args) throws InterruptedException {

        MyLinkedBlockingQueue<Integer> s = new MyLinkedBlockingQueue<>();

        Thread t1 = new Thread(() -> {
            try {
                s.put(7);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
               Integer a = s.take();
               System.out.println(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

    public static class MyLinkedBlockingQueue<E>{

        int capacity = Integer.MAX_VALUE;
        AtomicInteger count;

        Lock takeLock;
        Lock putLock;
        Condition notEmpty;
        Condition notFull;

        private static class Node<E>{

            Node next;

            E e;
            Node(E e){
                this.e = e;
            }
        }

        Node head;
        Node last;

        MyLinkedBlockingQueue(){
            this.count = new AtomicInteger();
            this.takeLock = new ReentrantLock();
            this.notEmpty = this.takeLock.newCondition();
            this.putLock = new ReentrantLock();
            this.notFull = this.putLock.newCondition();
            if (capacity <= 0) {
                throw new IllegalArgumentException();
            } else {
                this.last = this.head = new Node(null);
            }
        }

        public void put(E e) throws InterruptedException {

            Node node = new Node(e);
            int c = -1;

            putLock.lock();
            try{
                while(count.get() == capacity){
                    notFull.await();
                }

                last = last.next = node;
                c = count.getAndIncrement();
                if(c + 1 < capacity){
                    notFull.signal();
                }
            }finally {
                putLock.unlock();
            }

            if(c == 0){
                takeLock.lock();
                try {
                    notEmpty.signal();
                }finally {
                    takeLock.unlock();
                }
            }
        }

        public E take() throws InterruptedException {
            E e = null;
            int c = -1;
            takeLock.lock();
            try {
                while (count.get() == 0){
                    notEmpty.await();
                }
                Node f = head.next;
                this.head = f;
                e = (E) f.e;
                c = count.getAndDecrement();
            }finally {
                takeLock.unlock();
            }
            if(c == capacity){
                putLock.lock();
                try {
                    notFull.signal();
                }finally {
                    putLock.unlock();
                }
            }

            return e;
        }

    }


}
