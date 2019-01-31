package com.one.algorithm.queue;

import java.util.Iterator;

/**
 * @author: one
 * @date: 2019/1/31
 * @version: 1.0
 */
public class ArrayQueue<E> implements MyQueue<E> {
    
    private Object[] data = new Object[1];
    private int N = 0;
    private int first = 0;
    private int last = 0;

    private void check(){
        if (N >= data.length){
            resize(2 * data.length);
        }else if (N > 0 && N <= data.length/4){
            resize(data.length / 2);
        }
    }

    /**
     * 调整数组大小，使得栈具有伸缩性
     */
    private void resize(int size){
        Object[] temp = new Object[size];
        for (int i = 0; i < N; i++) {
            temp[i] = data[first++];
        }
        first = 0;
        last = N-1;
        data = temp;
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
        check();
        data[last++] = e;
        N++;
        return this;
    }

    @Override
    public E remove() throws Exception {
        if (isEmpty()){
            throw new Exception("queue is empty");
        }
        E value = (E) data[first];
        data[first++] = null;
        N--;
        return value;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }


    public static void main(String[] args) {
        Object[] a = new Object[3];
        System.out.println(a.length);
    }
}
