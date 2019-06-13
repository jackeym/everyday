package com.one.multithread;

import java.util.concurrent.*;

public class SemaphoreDemo {

    //表示老师只有10支笔
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {

        //表示50个学生
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100));
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "  同学准备获取笔......");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "  同学获取到笔");
                    System.out.println(Thread.currentThread().getName() + "  填写表格ing.....");
                    TimeUnit.SECONDS.sleep(3);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "  填写完表格，归还了笔！！！！！！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

}

