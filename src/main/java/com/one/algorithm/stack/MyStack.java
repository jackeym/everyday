package com.one.algorithm.stack;

/**
 * @author: one
 */
public interface MyStack <E> extends Iterable<E> {

    MyStack<E> push(E e);

    E pop() throws Exception;

    boolean isEmpty();

    int size();

}
