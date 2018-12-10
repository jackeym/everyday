package com.one.algorithm.string;

/**
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.
 * 则经过替换之后的字符串为We%20Are%20Happy。
 * @author: one
 */
public class ReplaceSpace {

    /*
    问题1：替换字符串，是在原来的字符串上做替换，还是新开辟一个字符串做替换！
    问题2：在当前字符串替换，怎么替换才更有效率（不考虑java里现有的replace方法）。
          从前往后替换，后面的字符要不断往后移动，要多次移动，所以效率低下
          从后往前，先计算需要多少空间，然后从后往前移动，则每个字符只为移动一次，这样效率更高一点。
          链接：https://www.nowcoder.com/questionTerminal/4060ac7e3e404ad1a894ef3e17650423
    */
    public String replaceSpace(StringBuffer str){
        int spacenum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' '){
                spacenum ++ ;
            }
        }
        
        int indexold = str.length() -1;
        int newlength = str.length() + spacenum * 2;
        int indexnew = newlength - 1;
        str.setLength(newlength);
        for (; indexold>=0 && newlength>indexold; --indexold) {
            if (str.charAt(indexold) == ' '){
                str.setCharAt(indexnew--, '0');
                str.setCharAt(indexnew--, '2');
                str.setCharAt(indexnew--, '%');
            }else {
                str.setCharAt(indexnew--, str.charAt(indexold));
            }
        }
        return str.toString();
    }
    
    /**
     * 做法1
     * @param str
     * @return
     */
    public String replaceSpace1(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            if (String.valueOf(s).equals(" ")){
                sb.append("%20");
            }else {
                sb.append(s);
            }
        }
        return sb.toString();
    }

    /**
     * 做法2
     * @param str
     * @return
     */
    public String replaceSpace2(StringBuffer str){
        return str.toString().replaceAll("\\s","%20");
    }
    
}
