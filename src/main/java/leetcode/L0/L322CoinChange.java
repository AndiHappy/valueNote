package leetcode.L0;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L322CoinChange {

 /**
 
 You are given coins of different denominations and a total amount of money amount. 
 Write a function to compute the fewest number of coins that you need to make up that amount. 
 If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.

  */
 
 
 /**
  * 动态规划的经典的案例，一步一步的实现动态规划的说明内容！
  * 首先是状态方程的确认：
  * f(n) = Matn.min{ f(n-c[i]) + 1  其中i为1到给定的coin数组len}
  * */
 /**
  * 暴力破解！
  * */
 public int coinChange(int[] coins, int amount) {
  if(amount ==0) return 0;
  int result = Integer.MAX_VALUE;
  for (int i = 0; i < coins.length; i++) {
   if(coins[i] > amount) continue;
   if(coins[i] <= amount) {
    int sub = coinChange(coins, amount-coins[i]);
    if(sub == -1) continue;
    //sub+1 写在这个地方真是精妙无双！
    result = Math.min(sub+1, result);
   }
  }
 return result==Integer.MAX_VALUE?-1:result;
 }
 
 /**
  * 暴力破解的前提下，采用cache子问题的机制
  * */
 public int coinChange_cache(int[] coins, int amount) {
     // 备忘录初始化为 -2
     int[] memo = new int[amount+1];
     Arrays.fill(memo, -2);
     return helper(coins, amount, memo);
 }

 int helper(int[] coins, int amount, int[] memo) {
     if (amount == 0) return 0;
     if (memo[amount] != -2) return memo[amount];
     int ans = Integer.MAX_VALUE;
     for (int coin : coins) {
         // 金额不可达
         if (amount - coin < 0) continue;
         int subProb = helper(coins, amount - coin, memo);
         // 子问题无解
         if (subProb == -1) continue;
         ans = Math.min(ans, subProb + 1);
     }
     // 记录本轮答案
     memo[amount] = (ans == Integer.MAX_VALUE) ? -1 : ans;
     return memo[amount];
 }
 
 
 /**
  * 上述的cache中已经记录了 0 到 amount 中某些阶段的 value值，我们声明的
  * DP数组，也就是在模拟这个数组。从下到上的计算！！
  * */
 int coinChange_dp(int[] coins, int amount) {
  int[] dp = new int[amount + 1];
  Arrays.fill(dp, amount + 1);
  dp[0] = 0;
  
  for (int i = 0; i < dp.length; i++) {
      // 内层 for 在求所有子问题 + 1 的最小值
      for (int coin : coins) {
          if (i - coin < 0) continue;
          dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
      }
  }
  return (dp[amount] == amount + 1) ? -1 : dp[amount];
}
 
/*
！！！！！其实算法：coinChange ， coinChange_cache，coinChange_dp 是相互递进的关系 ！！！！
*/
 
/**
【5，2，1】
 Coin: 5
   amount: 5
      Current route: 1
      Previous route: 2147483647
      Saving best route, dp[5] = 1
   amount: 6
   amount: 7
   amount: 8
   amount: 9
   amount: 10
      Current route: 2
      Previous route: 2147483647
      Saving best route, dp[10] = 2
   amount: 11

Coin: 2
   amount: 2
      Current route: 1
      Previous route: 2147483647
      Saving best route, dp[2] = 1
   amount: 3
   amount: 4
      Current route: 2
      Previous route: 2147483647
      Saving best route, dp[4] = 2
   amount: 5
   amount: 6
      Current route: 3
      Previous route: 2147483647
      Saving best route, dp[6] = 3
   amount: 7
      Current route: 2
      Previous route: 2147483647
      Saving best route, dp[7] = 2
   amount: 8
      Current route: 4
      Previous route: 2147483647
      Saving best route, dp[8] = 4
   amount: 9
      Current route: 3
      Previous route: 2147483647
      Saving best route, dp[9] = 3
   amount: 10
      Current route: 5
      Previous route: 2
      Saving best route, dp[10] = 2
   amount: 11
      Current route: 4
      Previous route: 2147483647
      Saving best route, dp[11] = 4
      
【1，2，5】
Coin: 1
   amount: 1
      Current route: 1
      Previous route: 2147483647
      Saving best route, dp[1] = 1
   amount: 2
      Current route: 2
      Previous route: 1
      Saving best route, dp[2] = 1
   amount: 3
      Current route: 2
      Previous route: 2147483647
      Saving best route, dp[3] = 2
   amount: 4
      Current route: 3
      Previous route: 2
      Saving best route, dp[4] = 2
   amount: 5
      Current route: 3
      Previous route: 1
      Saving best route, dp[5] = 1
   amount: 6
      Current route: 2
      Previous route: 3
      Saving best route, dp[6] = 2
   amount: 7
      Current route: 3
      Previous route: 2
      Saving best route, dp[7] = 2
   amount: 8
      Current route: 3
      Previous route: 4
      Saving best route, dp[8] = 3
   amount: 9
      Current route: 4
      Previous route: 3
      Saving best route, dp[9] = 3
   amount: 10
      Current route: 4
      Previous route: 2
      Saving best route, dp[10] = 2
   amount: 11
      Current route: 3
      Previous route: 4
      Saving best route, dp[11] = 3 
 * 
 * */
 
 
 /**
  * 时间超时，原因很简单在：findSumCondition，有了大量的重复的查找，如果加入缓存会如何？
  * 如何改进这个算法尼？
  * */
 public int coinChange_timeOut(int[] coins, int amount) {
  ArrayList<Integer> tmp = new ArrayList<Integer>();
  ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
  findSumCondition(coins,amount,tmp,result);
  int value = Integer.MAX_VALUE;
  for (int i = 0; i < result.size(); i++) {
   if(result.get(i).size() < value) {
    value = result.get(i).size();
   }
  }
  return result.size() > 0?value:-1;
 }
 
 private void findSumCondition(int[] coins, int amount, ArrayList<Integer> arrayList, ArrayList<ArrayList<Integer>> result) {
  if(amount == 0) {
   result.add(new ArrayList<Integer>(arrayList));
   return;
  }
  if(amount > 0) {
   for (int i = 0; i < coins.length; i++) {
    int tmp = coins[i];
    if(tmp <= amount) {
     arrayList.add(tmp);
     findSumCondition(coins, amount-tmp, arrayList,result);
     arrayList.remove(arrayList.size()-1);
    }
   }
  }
 }

 public static void main(String[] args) {
  L322CoinChange test = new L322CoinChange();
  System.out.println(test.coinChange(new int[] {1, 2, 5}, 11));
  System.out.println(test.coinChange(new int[] {1, 2, 5}, 13));

  
 }

}
