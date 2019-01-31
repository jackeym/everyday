package com.one.algorithm.queue;

import java.util.Iterator;

/**
 * 链表实现
 * 需要维护 first 和 last 节点指针，分别指向队首和队尾.
 * @author: one
 * @date: 2019/1/31
 * @version: 1.0
 */
public class ListQueue<E> implements MyQueue<E> {
    
    private Node<E> first;
    private Node<E> last;
    private int N = 0;

    private class Node<E>{
        E e;
        Node<E> next;

        public Node(){}
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
    }
    
    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public MyQueue<E> add(E e) {
        Node<E> newNode = new Node<E>(e, null);
        
        if (isEmpty()){
            first = newNode;
            last = newNode;
        }else {
            last.next = newNode;
            last = newNode;
        }
        N++;
        return this;
    }

    @Override
    public E remove() throws Exception {
        if (isEmpty()){
            throw new Exception("quene is empty");
        }
        Node<E> node = first;
        first = first.next;
        node.next = null; //释放原队列头元素的next引用
        N--;
        
        return node.e;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> cur = first;
            
            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public E next() {
                E e = cur.e;
                cur = cur.next;
                return e;
            }
        };
    }
}
