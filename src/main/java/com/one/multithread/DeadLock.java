package com.one.multithread;

/**
 * 死锁的场景一般是：线程 A 和线程 B 都在互相等待对方释放锁，或者是其中某个线程在释放锁的时候出现异常如死循环之类的。这时就会导致系统不可用。
 *
 * 常用的解决方案如下：
 *     尽量一个线程只获取一个锁。
 *     一个线程只占用一个资源。
 *     尝试使用定时锁，至少能保证锁最终会被释放。
 * @author: one
 */
public class DeadLock {
    private static Object locka = new Object();
    private static Object lockb = new Object();
    
    private void deadLock(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (locka){
                    try {
                        System.out.println(Thread.currentThread().getName()+ "in locka");
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    synchronized (lockb){
                        System.out.println(Thread.currentThread().getName()+ "in lockb");
                    }
                }
            }
        },"thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockb){
                    try {
                        System.out.println(Thread.currentThread().getName()+ "in lockb");
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    synchronized (locka){
                        System.out.println(Thread.currentThread().getName()+ "in locka");
                    }
                }
            }
        },"thread2");

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args){
        new DeadLock().deadLock();
    }
}
