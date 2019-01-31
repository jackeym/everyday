package com.one.algorithm.queue;

/**
 * @author: one
 * @date: 2019/1/31
 * @version: 1.0
 */
public interface MyQueue<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    MyQueue<E> add(E e);

    E remove() throws Exception;
}
