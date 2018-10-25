package com.one.multithread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者2
 * await()和signal()的功能基本上和wait() / nofity()相同，完全可以取代它们，
 * 但是它们和新引入的锁定机制Lock直接挂钩，具有更大的灵活性。
 * 通过在Lock对象上调用newCondition()方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全。
 * @author: one
 */
public class ProduceConsumer2 {
    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();
    // 锁
    private final Lock lock = new ReentrantLock();

    // 仓库满的条件变量
    private final Condition full = lock.newCondition();

    // 仓库空的条件变量
    private final Condition empty = lock.newCondition();

    // 生产产品
    public void produce(String producer) {
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            // 如果仓库已满
            while (list.size() == MAX_SIZE) {
                System.out.println("仓库已满，【" + producer + "】： 暂时不能执行生产任务!");
                try {
                    // 由于条件不满足，生产阻塞
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 生产产品
            list.add(new Object());

            System.out.println("【" + producer + "】：生产了一个产品\t【现仓储量为】:" + list.size());

            empty.signalAll();
        }finally {
            lock.unlock();
        }
       
    }

    // 消费产品
    public void consume(String consumer) {
        // 获得锁
        lock.lock();

        try {
            // 如果仓库存储量不足
            while (list.size() == 0) {
                System.out.println("仓库已空，【" + consumer + "】： 暂时不能执行消费任务!");
                try {
                    // 由于条件不满足，消费阻塞
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.remove();
            System.out.println("【" + consumer + "】：消费了一个产品\t【现仓储量为】:" + list.size());
            full.signalAll();
        }finally {
            // 释放锁
            lock.unlock();
        }

    }


    public static void main(String[] args)
    {
        ProduceConsumer2 storage=new ProduceConsumer2();

        for(int i=0;i<6;i++)
        {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    storage.produce(String.format("生成者%d:", finalI));
                }
            }).start();
        }

        for(int i=0;i<4;i++)
        {
            int finalI = i;
            new Thread(()-> storage.consume(String.format("消费者%d:", finalI))).start();
        }
    }
    
}
