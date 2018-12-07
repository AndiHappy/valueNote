package leetcode.L36ToL70;

/**
 * @author zhailz
 * 2018年12月5日 下午2:50:04
 */
public class L45JumpGameII {

	/**
	
	Given an array of non-negative integers, 
	
	you are initially positioned at the first index of the array.
	
	Each element in the array represents your maximum jump length at that position.
	
	Your goal is to reach the last index in the minimum number of jumps.
	
	Example:
	
	Input: [2,3,1,1,4]
	Output: 2
	Explanation: The minimum number of jumps to reach the last index is 2.
	Jump 1 step from index 0 to 1, then 3 steps to the last index.
	Note:
	
	You can assume that you can always reach the last index.
	 * */

	/**

问题：给定一个数组，每个元素表示你站在该位置可以跳的最大距离。假设你站在第一个元素，求你可以跳到最后元素的最小跳跃次数。

题目简洁，解题却不容易。

数组中的元素值是代表一个范围区间，题目需要求的是最小跳跃次数，也就是每一次的跳跃覆盖的范围应该尽可能远，这是一个大致思路。

假设 [ start, end ] 表示第 i 次跳跃才能到达的区间，nextEnd 代表在该区间中起跳的下一个最远元素，
那么，[ end+1, nextEnd ] 表示第 i+1 次跳才能去到的范围区间。

初始化 [start , end] 为 [0,0]，重复执行上面操作，直到 [start, end] 覆盖到终点元素。
由于 [start, end] 表示第 i  次跳跃才能到达的区间，所以 i 便是最小的跳跃次数。
在代码实现中， start  变量没有影响到程序的执行，加进去只是为了方便理解。


	 * 
	 * */
	public int jump(int[] nums) {
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
