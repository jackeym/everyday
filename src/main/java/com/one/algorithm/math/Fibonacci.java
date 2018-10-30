package com.one.algorithm.math;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。 n<=39
 * 可以肯定的是这一题通过递归的方式是肯定能做出来，但是这样会有一个很大的问题，那就是递归大量的重复计算会导致内存溢出。
 * 另外可以使用迭代法，用fn1和fn2保存计算过程中的结果，并复用起来。
 * (一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。)
 * 
 * @author: one
 */
public class Fibonacci {

    /**
     * 迭代法
     * @param number
     * @return
     */
    int fibonacci1(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        int first = 1, second = 1, third = 0;
        for (int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    /**
     * 递归
     */
    public int Fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1||n==2) {
            return 1;
        }
        return Fibonacci(n - 2) + Fibonacci(n - 1);
    }
}
