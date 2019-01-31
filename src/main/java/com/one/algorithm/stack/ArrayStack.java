package com.one.algorithm.stack;

import java.util.Iterator;

/**
 * 数组实现栈
 * @author: one
 */
public class ArrayStack<E> implements MyStack<E> {
    private Object[] data =  new Object[1];
    
    private int N = 0;  //元素数量
    
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
            temp[i] = data[i];
        }
        data = temp;
    }
    
    @Override
    public MyStack<E> push(E e) {
        check();
        data[N++] = e;
        return this;
    }

    @Override
    public E pop() throws Exception {
        if (isEmpty()){
            throw new Exception("stack is empty");
        }
        E e = (E) data[--N];
        
        check();
        // 避免对象游离
        data[N] = null;
        
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
            private int i = N;
            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public E next() {
                return (E) data[--i];
            }
        };
    }
}
