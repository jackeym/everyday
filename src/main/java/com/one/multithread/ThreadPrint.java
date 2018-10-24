package com.one.multithread;

/**
 * 两个线程交替打印出100以内的奇数和偶数
 * 方式一：synchronized，wait/notify
 * @author: one
 */
public class ThreadPrint {
    private static int cnt = 1;

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread1 = new Thread(myThread);
        Thread thread2 = new Thread(myThread);
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

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    this.notify();
                    if (cnt <= 100){
                        String currentThread = Thread.currentThread().getName();
                        System.err.println(currentThread +":"+cnt);
                        cnt ++;
                        try {
                            this.wait();
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
