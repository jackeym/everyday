package com.one.algorithm.math;

/**
 * 数值的整数次方
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * @author: one
 */
public class Power {

    /**
     * 方法1
     * 我这里采用的是二分幂思想，当然也可以采用快速幂。 更具剑指offer书中细节，该题的解题思路如下： 
     * 1.当底数为0且指数<0时，会出现对0求倒数的情况，需进行错误处理，设置一个全局变量； 
     * 2.判断底数是否等于0，由于base为double型，所以不能直接用==判断 
     * 3.优化求幂函数（二分幂）。 当n为偶数，a^n =（a^n/2）*（a^n/2）； 当n为奇数，a^n = a^[(n-1)/2] * a^[(n-1)/2] * a。
     * 时间复杂度O(logn)
     */
    public double power1(double base, int exponent){
        if (equal(base, 0.0) && exponent < 0){
             return 0.0;   
        }
        int absexponent = exponent;
        //如果指数小于0，将指数转正
        if(exponent<0)
            absexponent=-exponent;

        //getPower方法求出base的exponent次方。
        double res=getPower(base,absexponent);
        //如果指数小于0，所得结果为上面求的结果的倒数
        if(exponent<0)
            res=1.0/res;
        return res;
        
    }

    //求出b的e次方的方法
    double getPower(double b,int e){
        //如果指数为0，返回1
        if (e == 0){
            return 1.0;
        }
        //如果指数为1，返回b
        if (e == 1){
            return b;
        }
        //e>>1相等于e/2，这里就是求a^n =（a^n/2）*（a^n/2）
        double result = getPower(b, e>>1);
        result *= result;
        if ((e&1)==1){
            result *= b;
        }
        return result;
    }

    /**
     * 方法2
     * 累乘。不过这种方法的时间复杂度为O（n），这样没有前一种方法效率高。
     */
    
    public double power2(double base, int exponent){
        double result = 0.0;
        for (int i = 0; i < Math.abs(exponent); i++) {
            result *= base;
        }
        if (exponent < 0){
            return 1/result;
        }
        return result;
    }

    //比较两个double型变量是否相等的方法
    boolean equal(double num1,double num2){
        if(num1-num2>-0.000001&&num1-num2<0.000001)
            return true;
        else
            return false;
    }
}
