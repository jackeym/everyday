package com.one.algorithm.array;

/**
 * 这次改成股票可以买卖多次, 但是你必须要在出售股票之前把持有的股票卖掉。
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * @date: 2019/4/9
 * @version: 1.0
 */
public class StockSale2 {

    public static int maxProfit(int[] prices) {
        // 7,1,5,3,6,4
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i != 0 && prices[i] - prices[i-1] > 0) {
                maxProfit += prices[i] - prices[i-1];
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
