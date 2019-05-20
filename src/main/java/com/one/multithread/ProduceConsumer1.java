package com.one.multithread;

import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * 生产者消费者1
 * wait() / nofity()方法是基类Object的两个方法：
 *     wait()方法：当缓冲区已满/空时，生产者/消费者线程停止自己的执行，放弃锁，使自己处于等等状态，让其他线程执行。
 *     notify()方法：当生产者/消费者向缓冲区放入/取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
 * @author: one
 */
public class ProduceConsumer1 {
    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

    // 生产产品
    public void produce(String producer)
    {
        synchronized (list)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 如果仓库已满
            while (list.size() == MAX_SIZE)
            {
                System.out.println("仓库已满，【"+producer+"】： 暂时不能执行生产任务!");
                try
                {
                    // 由于条件不满足，生产阻塞
                    list.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            // 生产产品            
            list.add(new Object());

            System.out.println("【"+producer+"】：生产了一个产品\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    // 消费产品
    public void consume(String consumer)
    {
        synchronized (list)
        {
            //如果仓库存储量不足
            while (list.size()==0)
            {
                System.out.println("仓库已空，【"+consumer+"】： 暂时不能执行消费任务!");
                try
                {
                    // 由于条件不满足，消费阻塞
                    list.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            list.remove();
            System.out.println("【"+consumer+"】：消费了一个产品\t【现仓储量为】:" + list.size());
            list.notifyAll();
        }
    }


    public static void main(String[] args)
    {
        ProduceConsumer1 storage=new ProduceConsumer1();

        ExecutorService service = new ThreadPoolExecutor(15, 15, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100));;
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    storage.produce(String.format("生成者%d:", finalI));
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            service.submit(()-> storage.consume(String.format("消费者%d:", finalI)));
        }

        
//        for(int i=0;i<6;i++)
//        {
//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    storage.produce(String.format("生成者%d:", finalI));
//                }
//            }).start();
//        }
//
//        for(int i=0;i<4;i++)
//        {
//            int finalI = i;
//            new Thread(()-> storage.consume(String.format("消费者%d:", finalI))).start();
//        }
        
        
    }
    
}
