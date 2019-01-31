package com.one.algorithm.stack;

import java.util.Iterator;

/**
 * 链表实现栈
 * 需要使用链表的头插法来实现，因为头插法中最后压入栈的元素在链表的开头，它的 next 指针指向前一个压入栈的元素，
 * 在弹出元素时就可以通过 next 指针遍历到前一个压入栈的元素从而让这个元素成为新的栈顶元素。
 * @author: one
 * @date: 2019/1/31
 * @version: 1.0
 */
public class ListStack<E> implements MyStack<E> {

    private Node<E> top = null; //栈顶元素
    private int N = 0;
    
    //链栈的节点
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
    public MyStack<E> push(E e) {
        top = new Node<E>(e, top);
        N++ ;
        return this;
    }

    @Override
    public E pop() throws Exception {
        if (isEmpty()){
            throw new Exception("stack is empty");
        }
        E e = top.e;
        
        top = top.next;
        N--;
        
        return e;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            
            private Node<E> cur= top;
            
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
