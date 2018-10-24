package com.one.multithread;

/**
 * 三个线程交替打印AB
 * synchronized，wait/notify
 * @author: one
 */
public class ThreadPrintABC1 {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Thread thread1 = new Thread(new MyThread("A", c, a));
        Thread thread2 = new Thread(new MyThread("B", a, b));
        Thread thread3 = new Thread(new MyThread("C", b, c));
        try {
            thread1.start();
            Thread.sleep(100);
            thread2.start();
            Thread.sleep(100);
            thread3.start();
            Thread.sleep(100);
            
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("END!");
    }
    
    private static class MyThread implements Runnable {

        private String name;
        private Object prev;
        private Object self;

        private MyThread(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                synchronized (prev) {
                    synchronized (self) {
                        System.out.print(name);
                        count--;

                        self.notify();
                    }
                    try {
                        prev.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
    
}
