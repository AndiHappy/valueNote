package leetcode.L112ToL129;

/**
 * @author guizhai
 *
 */
public class L123BestTimetoBuyandSellStockIII {

	/**
	
	
	Say you have an array for which the ith element is the price of a given stock on day i.
	
	Design an algorithm to find the maximum profit. You may complete at most two transactions.
	
	Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
	
	Example 1:
	
	Input: [3,3,5,0,0,3,1,4]
	Output: 6
	Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
	           Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
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

	/*
	The thinking is simple and is inspired by the best solution from Single Number II (I read through the discussion after I use DP).
	Assume we only have 0 money at first;
	4 Variables to maintain some interested 'ceilings' so far:
	The maximum of if we've just buy 1st stock, if we've just sold 1nd stock, if we've just buy 2nd stock, if we've just sold 2nd stock.
	Very simple code too and work well. I have to say the logic is simple than those in Single Number II.
	*/
	public int maxProfit(int[] prices) {
		int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
		int release1 = 0, release2 = 0;
		for (int i : prices) { // Assume we only have 0 money at first
			release2 = Math.max(release2, hold2 + i); // The maximum if we've just sold 2nd stock so far.
			hold2 = Math.max(hold2, release1 - i); // The maximum if we've just buy  2nd stock so far.
			release1 = Math.max(release1, hold1 + i); // The maximum if we've just sold 1nd stock so far.
			hold1 = Math.max(hold1, -i); // The maximum if we've just buy  1st stock so far. 
		}
		return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
	}

	/*
	More readable code with clearer explanation:
	
	
	For instance, in the case 3,3,5,0,0,3,1,4:
	
	the max profit is (4 - 1) + (3 - 0), which is also equal to 4 - (1 - (3 - 0))
	
	'0' is the min of cost1, (3 - 0) is the profit1, 
	
	(1 - (3 - 0)) is the cost2, 
	
	and 4 - (1 - (3 - 0)) is the profit2
	
	
	this also works for the increasing sequence, say, 1,2,3,4, 
	
	the max profit is 4 - (4 - (4 - 1))
	
	in order to get the max profit eventually, profit1 must be as relatively most as possible to produce a small cost2, 
	
	and therefore cost2 can be as less as possible to give us the final max profit2. 
	
	So now we understand why and where we need to take min or max of all cost and profit variables.....
	
	Here is my code rewritten based on @SmileChen 's previous post:
	
	*/
	public int maxProfit_explain(int[] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}
		int cost1 = Integer.MAX_VALUE, cost2 = Integer.MAX_VALUE, profit1 = 0, profit2 = 0;

		for (int price : prices) {
			cost1 = Math.min(cost1, price);
			profit1 = Math.max(profit1, price - cost1);
			cost2 = Math.min(cost2, price - profit1);
			profit2 = Math.max(profit2, price - cost2);
		}
		return profit2;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
