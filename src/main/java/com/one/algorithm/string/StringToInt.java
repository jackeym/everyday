package com.one.algorithm.string;

/**
 * 字符串转换成整数
 * @author: one
 */
public class StringToInt {
    
    public int strToInt(String str){
        if (str.length() == 0) 
            return 0;
        char[] chars = str.toCharArray();
        int flag = 0;   //符号位
        if (chars[0] == '+'){
            flag = 1;
        }else if (chars[0] == '-'){
            flag = 2;
        }
        int start = flag>0?1:0;
        int res = 0;    //结果
        for (int i = start; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) 
                return 0;
            int temp = chars[i] - '0';
            res = res * 10 + temp;
        }
        
        return flag==2?-res:res;
    }

    public static void main(String[] args) {
        StringToInt s = new StringToInt();
        System.out.println(s.strToInt("  "));
    }
}
