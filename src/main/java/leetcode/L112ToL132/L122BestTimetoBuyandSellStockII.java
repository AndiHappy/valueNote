package leetcode.L112ToL132;

/**
 * @author guizhai
 *
 */
public class L122BestTimetoBuyandSellStockII {

	/**
	
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

	 */
	
	/**
	 *  because realizing the peak-valley pattern is a crucial step.
	 * */
	public int maxProfit(int[] prices) {
    int total = 0;
    for (int i=0; i< prices.length-1; i++) {
    	
        if (prices[i+1]>prices[i]) total += prices[i+1]-prices[i];
    }
    
    return total;
}

	/**
	 * This is not the canonical(规范) solution, 
	 * but just wanted to post a DP to greedy reduction since we are always making the greedy choice. 
	 * 
	 * At any given day, the max gain includes the current day or it is the gain from the previous day. 
	 * 
	 * If it includes the current day i (i.e., sell) then identify a j 
	 * such that we make a profit between j and i in addition to profit at j.
	 * 
	 * */
	
	/**
	 * 这里的思路就比较的容易理解
	 * 首先是int[] G 标识第i天的情况下，最大的利润，那么G[0] = 0
	 * G[1] 就需要看price[1] - price[0] 和 0 那个大了，
	 * 由此得出来一个公式：
	 * G[i] = max{G[i-1], G[j]+ price[i]-price[j]} 其中j为0 到 i
	 * 然后就有了下面的代码：
	 * */
	int maxProfit_greedy_think(int[] prices) {
    if (prices == null || prices.length <= 1) return 0;
    int[] G = new int[prices.length];
    for (int i = 1; i < prices.length; ++i) {
        int g = G[i - 1];
        for (int j = 0; j < i; ++j) {
            if (prices[i] > prices[j]) {
                g = Math.max(g, G[j] + prices[i] - prices[j]);
            }
        }
        G[i] = g;
    }
    return G[prices.length - 1];
}
	
	/**
	 * 
	 * After eliminating the greedy choice... i.e., inner loop is making the greedy choice.
	 * 对内循环做出了优化，对于从0 到 i 的 j的循环来说，就是贪心的特质
	 * */
	int maxProfit_greedy(int[] prices) {
    if (prices == null || prices.length <= 1) return 0;
    int[] G = new int[prices.length];
    int sellPrice = prices[0];
    for (int i = 1; i < prices.length; ++i) {
        G[i] = Math.max(G[i - 1],prices[i]-sellPrice);
        sellPrice = Math.min(prices[i]-G[i], sellPrice);
    }
    return G[prices.length - 1];
}
	
	
	
	
	public static void main(String[] args) {
		L122BestTimetoBuyandSellStockII test = new L122BestTimetoBuyandSellStockII();
//		int[] prices = new int[] {1,2,3,4,5};
		int[] prices = new int[] {7,1,5,3,6,4};
		test.maxProfit_greedy(prices );

	}

}
