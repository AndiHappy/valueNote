/**
 * 
 */
package leetcode.L36ToL70;

import java.util.HashMap;

/**
 * @author guizhai
 *
 */
public class L70ClimbingStairs {

	/**
	  
	You are climbing a stair case. It takes n steps to reach to the top.
	
	Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	
	Note: Given n will be a positive integer.
	
	Example 1:
	
	Input: 2
	Output: 2
	Explanation: There are two ways to climb to the top.
	1. 1 step + 1 step
	2. 2 steps
	Example 2:
	
	Input: 3
	Output: 3
	Explanation: There are three ways to climb to the top.
	1. 1 step + 1 step + 1 step
	2. 1 step + 2 steps
	3. 2 steps + 1 step
	
	
	Input: 4
	Output: 3
	Explanation: There are three ways to climb to the top.
	a[3]
	a[2]
	
	 */

	public int climbStairs(int n) {
		if(n ==0 || n == 1 || n == 2) return n;
		int[] a = new int[n+1];
		a[0] = 0;
		a[1] = 1;
		a[2] = 2;
		for (int i = 3; i <= n; i++) {
			a[i] = a[i - 1] + a[i - 2];
		}
		return a[n];
	}

	public int climbStairs_(int n) {
		HashMap<Integer, Integer> memory = new HashMap<Integer, Integer>();
		return utilityStairsClimb(n, memory);

	}


	public int utilityStairsClimb(int remain, HashMap<Integer, Integer> memory) {

		int val;

		if (remain == 0)
			return 1;
		if (remain < 0)
			return 0;
		if (memory.containsKey(remain))
			return memory.get(remain);

		val = utilityStairsClimb(remain - 1, memory) + utilityStairsClimb(remain - 2, memory);
		memory.put(remain, val);

		return val;


	}

	public static void main(String[] args) {
		L70ClimbingStairs test = new L70ClimbingStairs();
		System.out.println(test.climbStairs(0)+" "+ test.climbStairs_(0));
		System.out.println(test.climbStairs(1)+" "+ test.climbStairs_(1));
		System.out.println(test.climbStairs(3)+" "+ test.climbStairs_(3));
		System.out.println(test.climbStairs(4)+" "+ test.climbStairs_(4));
		System.out.println(test.climbStairs(5)+" "+ test.climbStairs_(5));
		System.out.println(test.climbStairs(6)+" "+ test.climbStairs_(6));
		System.out.println(test.climbStairs(7)+" "+ test.climbStairs_(7));
		System.out.println(test.climbStairs(8)+" "+ test.climbStairs_(8));
		System.out.println(test.climbStairs(9)+" "+ test.climbStairs_(9));
		System.out.println(test.climbStairs(10)+" "+ test.climbStairs_(10));

		

	}

}
