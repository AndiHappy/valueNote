package leetcode.L112ToL129;

public class L188BestTimetoBuyandSellStockIV {

	/**
	 * 
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
	 * */
	
	
	/*
	The general idea is DP, while I had to add a "quickSolve" function to tackle some corner cases to avoid TLE.

	DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
*/
	
	    public int maxProfit(int k, int[] prices) {
	        int len = prices.length;
	        if (k >= len / 2) return quickSolve(prices);
	        
	        int[][] t = new int[k + 1][len];
	        for (int i = 1; i <= k; i++) {
	            int tmpMax =  -prices[0];
	            for (int j = 1; j < len; j++) {
	                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
	                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
	            }
	        }
	        return t[k][len - 1];
	    }
	    

	    private int quickSolve(int[] prices) {
	        int len = prices.length, profit = 0;
	        for (int i = 1; i < len; i++)
	            // as long as there is a price gap, we gain a profit.
	            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
	        return profit;
	    }
	    
	    
	    
	    /**
	     * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
	     *  
	     * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
	     *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
	     * dp[0, j] = 0; 0 transactions makes 0 profit
	     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
	     */

	    public int maxProfit_e(int k, int[] prices) {
	    	int n = prices.length;
	    	if (n <= 1)
	    		return 0;
	    	
	    	//if k >= n/2, then you can make maximum number of transactions.
	    	if (k >=  n/2) {
	    		int maxPro = 0;
	    		for (int i = 1; i < n; i++) {
	    			if (prices[i] > prices[i-1])
	    				maxPro += prices[i] - prices[i-1];
	    		}
	    		return maxPro;
	    	}
	    	
	        int[][] dp = new int[k+1][n];
	        for (int i = 1; i <= k; i++) {
	        	int localMax = dp[i-1][0] - prices[0];
	        	for (int j = 1; j < n; j++) {
	        		dp[i][j] = Math.max(dp[i][j-1],  prices[j] + localMax);
	        		localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
	        	}
	        }
	        return dp[k][n-1];
	    }
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
