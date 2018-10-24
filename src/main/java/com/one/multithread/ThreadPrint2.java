package com.one.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印出100以内的奇数和偶数
 * 方式二：ReentrantLock，
 * @author: one
 */
public class ThreadPrint2 {
    private static int cnt = 1;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    public static void main(String[] args) {
        
        Thread thread1 = new Thread(new MyThread(condition1,condition2));
        Thread thread2 = new Thread(new MyThread(condition2,condition1));
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("END!");
    }
    

    
    private static class MyThread implements Runnable{
        private Condition c1;
        private Condition c2;

        public MyThread(Condition c1,Condition c2){
            this.c1=c1;
            this.c2=c2;
        }
        
        @Override
        public void run() {
            while (true){
                lock.lock();
                c2.signal();
                if (cnt <= 100){
                    String currentThread = Thread.currentThread().getName();
                    System.err.println(currentThread +":"+cnt);
                    cnt ++;
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }else {
                    lock.unlock();
                    return;
                }
            }
        }
    }
}
