package com.one.algorithm.lru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

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


    private HashMap<Integer, Node>
            cache = new HashMap<Integer, Node>();
    private int count;
    private int capacity;
    private Node head, tail;

    public LRUMap(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new Node();
        head.pre = null;

        tail = new Node();
        tail.next = null;

        head.next = tail;
        tail.pre = head;
    }


    /**
     * 总是在头节点中插入新节点.
     */
    private void addNode(Node node) {

        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    /**
     * 摘除一个节点.
     */
    private void removeNode(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;
    }

    /**
     * 摘除一个节点,并且将它移动到开头
     */
    private void moveToHead(Node node) {
        this.removeNode(node);
        this.addNode(node);
    }

    /**
     * 弹出最尾巴节点
     */
    private Node popTail() {
        Node res = tail.pre;
        this.removeNode(res);
        return res;
    }

    public int get(int key) {

        Node node = cache.get(key);
        if (node == null) {
            return -1; // cache里面没有
        }

        // cache 命中,挪到开头
        this.moveToHead(node);

        return node.value;
    }


    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node == null) {

            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++count;

            if (count > capacity) {
                // 最后一个节点弹出
                Node tail = this.popTail();
                this.cache.remove(tail.key);
                count--;
            }
        } else {
            // cache命中,更新cache.
            node.value = value;
            this.moveToHead(node);
        }
    }


    private static class Node {
        int key;
        int value;
        Node pre;
        Node next;
    }
}