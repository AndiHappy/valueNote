package leetcode.L112ToL129;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guizhai
 *
 */
public class L121BestTimetoBuyandSellStock {

	/**
	 * 
	
	Say you have an array for which the ith element is the price of a given stock on day i.
	
	If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
	design an algorithm to find the maximum profit.
	
	Note that you cannot sell a stock before you buy one.
	
	Example 1:
	
	Input: [7,1,5,3,6,4]
	
	Output: 5
	Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
	           Not 7-1 = 6, as selling price needs to be larger than buying price.
	Example 2:
	
	Input: [7,6,4,3,1]
	Output: 0
	Explanation: In this case, no transaction is done, i.e. max profit = 0.
	
	 */

	/*
	
	The logic to solve this problem is same as "max subarray problem" using Kadane's Algorithm. 
	Since no body has mentioned this so far, I thought it's a good thing for everybody to know.
	
	All the straight forward solution should work, but if the interviewer twists the question slightly 
	by giving the difference array of prices, Ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7}, 
	you might end up being confused.
	
	Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original array, 
	and find a contiguous subarray giving maximum profit. If the difference falls below 0, reset it to zero.
	
	    public int maxProfit(int[] prices) {
	        int maxCur = 0, maxSoFar = 0;
	        for(int i = 1; i < prices.length; i++) {
	            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
	            maxSoFar = Math.max(maxCur, maxSoFar);
	        }
	        return maxSoFar;
	    }
	*maxCur = current maximum value
	
	*maxSoFar = maximum value found so far
	*
	*/

	/*
	Kadane's Algorithm 算法
	
  Example 1:
	
	Input: [7,1,5,3,6,4]
	prices[i] - prices[i - 1] 形成数组是：
	0，-6，4，-2，3，-2
	
	Output: 5
	
	 * */
	
	public int maxProfit(int[] prices) {
		int maxCur = 0, maxSoFar = 0;
		for (int i = 1; i < prices.length; i++) {
			maxCur = Math.max(0, maxCur += prices[i] - prices[i - 1]);
			maxSoFar = Math.max(maxCur, maxSoFar);
		}
		return maxSoFar;
	}
	
	public int maxProfit_stright(int[] prices) {
		int result = 0;
		int buy = prices[0];
		for (int i = 0; i < prices.length; i++) {
			if(prices[i]<buy) {
				buy = prices[i];
			}else if(result <(prices[i] - buy)) {
				result = prices[i] - buy;
			}
		}
		return result;
	}


	//	State defination:
	
	//		f[x] is a[x] profit
	//		Then result is max{f[x]}
	
	//		State Transfer Function:
	//		if p<x, a[p]<a[x],
	//		f[x] = f[p] + (a[x] - a[p])
	//		Here is the code using DP:

	public int maxProfit_DP(int[] prices) {
		int res = 0;
		if (prices == null || prices.length == 0)
			return res;
		int[] a = prices;
		
		int len = prices.length;
		Map<Integer, Integer> f = new HashMap<>();
		for (int i = 0; i < len; i++) {
			f.put(i, 0);
		}
		
		for (int x = 0; x < len; x++) {
			for (int p = 0; p < x; p++) {
				if (a[x] > a[p]) {
					f.put(x, f.get(p) + (a[x] - a[p]));
				}
			}
		}
		
		for (int i = 0; i < len; i++) {
			res = Math.max(res, f.get(i));
		}
		
		return res;
	}

	public static void main(String[] args) {


	}

}
