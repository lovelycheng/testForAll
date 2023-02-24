package tech.lovelycheng.demo.test.javal.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chengtong
 * @date 2023/2/21 14:08
 */
public class Test {

    static volatile int d = 0;

    public static void main(String[] args) throws InterruptedException {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Condition condition = lock.writeLock().newCondition();
        Thread t1 = new Thread(() ->{
            lock.writeLock().lock();
            try{
                int dd = d ;
                while(dd != 1){
                    System.out.println("t1 blocked");
                    condition.await();
                    System.out.println("t1 waked");
                    dd = d;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.writeLock().unlock();
            }
        });
        Thread t = new Thread(() -> {
            lock.writeLock().lock();
            try{
                Thread.sleep(100);
                if(d != 1){
                    d = 1;
                }
                System.out.println("wake up t1");
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.writeLock().unlock();
            }
        });
        t1.start();
        t.start();
        t1.join();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        Thread t3 = new Thread(()->{
            readLock.lock();
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                readLock.unlock();
            }
        });
        Thread t4 = new Thread(()->{
            readLock.lock();
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                readLock.unlock();
            }
        });
        t3.start();
        t4.start();
    }
}
