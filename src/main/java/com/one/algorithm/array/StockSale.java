package com.one.algorithm.array;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 
 * @date: 2019/4/9
 * @version: 1.0
 */
public class StockSale {

    public static int maxProfit(int[] prices) {
        // 7,1,5,3,6,4
        int maxProfit = 0;
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (Integer.MAX_VALUE != minNum && prices[i] - minNum > maxProfit) {
                maxProfit = prices[i] - minNum;
            }

            if (prices[i] < minNum) {
                minNum = prices[i];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] a = new int[]{7,1,5,3,6,4};
        int max = maxProfit(a);
        System.out.println(max);
    }
}
