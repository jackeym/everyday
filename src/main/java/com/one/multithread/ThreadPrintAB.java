package com.one.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: one
 */
public class ThreadPrintAB {

    private static Object obj = new Object();

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    
    public static void main(String[] args) {
        // 方式一
//        Thread thread1 = new Thread(new MyThread("A"));
//        Thread thread2 = new Thread(new MyThread("B"));
        
        // 方式二
        Thread thread1 = new Thread(new MyThread2("A",condition1,condition2));
        Thread thread2 = new Thread(new MyThread2("B",condition2,condition1));
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

    private static class MyThread2 implements Runnable{
        private String name;
        private Condition c1;
        private Condition c2;

        public MyThread2(String name,Condition c1,Condition c2){
            this.name = name;
            this.c1=c1;
            this.c2=c2;
        }

        @Override
        public void run() {
            int i = 0;
            while (true){
                lock.lock();
                c2.signal();
                if (i <= 10){
                    String currentThread = Thread.currentThread().getName();
                    System.err.println(currentThread +":"+ name);
                    i ++;
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
    
    private static class MyThread implements Runnable{
        private String name;

        public MyThread(String name){
            this.name = name;
        }

        @Override
        public void run() {
            int i = 0;
            while (true){
                synchronized (obj){
                    obj.notify();
                    if (i <= 10){
                        String currentThread = Thread.currentThread().getName();
                        System.err.println(currentThread +":"+ name);
                        i ++;
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        return;
                    }
                }
            }
        }
    }
}
