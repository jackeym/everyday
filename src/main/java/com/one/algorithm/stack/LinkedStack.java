package com.one.algorithm.stack;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * 链表实现栈
 * 需要使用链表的头插法来实现，因为头插法中最后压入栈的元素在链表的开头，它的 next 指针指向前一个压入栈的元素，
 * 在弹出元素时就可以通过 next 指针遍历到前一个压入栈的元素从而让这个元素成为新的栈顶元素。
 * @author: one
 * @date: 2019/1/31
 * @version: 1.0
 */
public class LinkedStack<T> implements MyStack<T> ,Serializable {

    private static final long serialVersionUID = 1911829302658328353L;

    private Node<T> top;

    private int size;
    
    //链栈的节点
    private class Node<T>{
        T data;
        Node<T> next;

        public Node(){}
        public Node(T data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    public LinkedStack(){
        this.top=new Node<>();
    }

    public int size(){
        return size;
    }


    @Override
    public boolean isEmpty() {
        return top==null || top.data==null;
    }

    @Override
    public void push(T data) {
        if (data==null){
            throw new RuntimeException("data can\'t be null");
        }
        if(this.top == null){//调用pop()后top可能为null
            this.top = new Node<>(data,null);
        }else {
            Node<T> p = new Node<>(data,this.top);
            top = p;//更新栈顶
        }
        size++;
    }

    @Override
    public T peek()  {
        if(isEmpty()){
            throw new EmptyStackException();
        }

        return top.data;
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }

        T data=top.data;
        top=top.next;
        size--;
        return data;
    }
    //测试
    public static void main(String[] args){
        LinkedStack<String> sl=new LinkedStack<>();
        sl.push("A");
        sl.push("B");
        sl.push("C");
        int length=sl.size();
        for (int i = 0; i < length; i++) {
            System.out.println("sl.pop->"+sl.pop());
        }
    }
}
