package tech.lovelycheng.demo.test.javal.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author chengtong
 * @date 2023/2/9 14:10
 */
public class LockDesign implements Lock {

    private transient volatile Thread owner;

    private class Node{
        Thread thread;
        Node next;
        Node prev;
        /**
         * status
         *  0: blocked
         *  1:
         */
        int status;
    }

    @Override
    public void lock() {
        Thread current = Thread.currentThread();
        if(current == this.owner){

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
