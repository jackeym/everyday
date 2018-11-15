package com.one.algorithm.lru;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Function:
 *  链表实现
 * 1.每次写入数据时将数据放入链表头结点。
 * 2.使用数据时候将数据移动到头结点。
 * 3.缓存数量超过阈值时移除链表尾部数据。
 *
 * @author crossoverJie
 *         Date: 03/04/2018 00:08
 * @since JDK 1.8
 */
public class LRUMap<K, V> {
    private final static Logger LOGGER = LoggerFactory.getLogger(LRUMap.class);
    
    private final Map<K, V> cacheMap = new HashMap<>();

    /**
     * 最大缓存大小
     */
    private int cacheSize;

    /**
     * 节点大小
     */
    private int nodeCount;


    /**
     * 头结点
     */
    private Node<K, V> header;

    /**
     * 尾结点
     */
    private Node<K, V> tailer;


    /**
     * 超时时间
     */
    private final static Long EXPIRE_TIME = 60 * 60 * 1000L ;

    /**
     * 检查是否超期线程
     */
    private ExecutorService checkTimePool ;

    public LRUMap(int cacheSize) {
        this.cacheSize = cacheSize;
        //头结点的下一个结点为空
        header = new Node<>();
        header.next = null;

        //尾结点的上一个结点为空
        tailer = new Node<>();
        tailer.tail = null;

        //双向链表 头结点的上结点指向尾结点
        header.tail = tailer;

        //尾结点的下结点指向头结点
        tailer.next = header;

        //开启一个线程检查最先放入队列的值是否超期
        executeCheckTime();
    }

    /**
     * 开启一个线程检查最先放入队列的值是否超期 设置为守护线程
     */
    private void executeCheckTime() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("check-thread-%d")
                .setDaemon(true)
                .build();
        checkTimePool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        checkTimePool.execute(new CheckTimeThread()) ;

    }

    public void put(K key, V value) {
        cacheMap.put(key, value);

        //双向链表中添加结点
        addNode(key, value);
    }

    public V get(K key){

        Node<K, V> node = getNode(key);

        //移动到头结点
        moveToHead(node) ;

        return cacheMap.get(key);
    }

    private void moveToHead(Node<K,V> node){

        //如果是尾节点
        if (node.tail == null){
            node.next.tail = null ;
            tailer = node.next ;
            nodeCount -- ;
        }

        //如果是本来就是头节点 不作处理
        if (node.next == null){
            return ;
        }

        //如果处于中间节点
        if (node.tail != null && node.next != null){
            //它的上一节点指向它的下一节点 也就删除当前节点
            node.tail.next = node.next ;
            nodeCount -- ;
        }

        //最后在头部增加当前节点
        //注意这里需要重新 new 一个对象，不然原本的node 还有着下面的引用，会造成内存溢出。
        node = new Node<>(node.getKey(),node.getValue()) ;
        addHead(node) ;

    }

    /**
     * 链表查询 效率较低
     * @param key
     * @return
     */
    private Node<K,V> getNode(K key){
        Node<K,V> node = tailer ;
        while (node != null){

            if (node.getKey().equals(key)){
                return node ;
            }

            node = node.next ;
        }

        return null ;
    }


    /**
     * 写入头结点
     * @param key
     * @param value
     */
    private void addNode(K key, V value) {

        Node<K, V> node = new Node<>(key, value);

        //容量满了删除最后一个
        if (cacheSize == nodeCount) {
            //删除尾结点
            delTail();
        }

        //写入头结点
        addHead(node);

    }


    /**
     * 添加头结点
     *
     * @param node
     */
    private void addHead(Node<K, V> node) {

        //写入头结点
        header.next = node;
        node.tail = header;
        header = node;
        nodeCount++;

        //如果写入的数据大于2个 就将初始化的头尾结点删除
        if (nodeCount == 2) {
            tailer.next.next.tail = null;
            tailer = tailer.next.next;
        }

    }

    private void delTail() {
        //把尾结点从缓存中删除
        cacheMap.remove(tailer.getKey());

        //删除尾结点
        tailer.next.tail = null;
        tailer = tailer.next;

        nodeCount--;

    }

    private class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> tail;
        Node<K, V> next;
        private Long updateTime ;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.updateTime = System.currentTimeMillis() ;
        }

        public Node() {
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        Node<K,V> node = tailer ;
        while (node != null){
            sb.append(node.getKey()).append(":")
                    .append(node.getValue())
                    .append("-->") ;

            node = node.next ;
        }


        return sb.toString();
    }

    private class CheckTimeThread implements Runnable{

        @Override
        public void run() {
            while (nodeCount > 0){
                try {
                    Node node = tailer;
                    if (node == null){
                        continue ;
                    }
                    Long updateTime = node.getUpdateTime() ;

                    if ((updateTime - System.currentTimeMillis()) >= EXPIRE_TIME){
                        delTail();
                    }
                } catch (Exception e) {
                    LOGGER.error("InterruptedException");
                }
            }
        }
    }
}