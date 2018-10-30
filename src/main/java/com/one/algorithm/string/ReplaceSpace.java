package com.one.algorithm.string;

/**
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * @author: one
 */
public class ReplaceSpace {
    /**
     * 做法1
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
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
