package leetcode.L36ToL70;

/**
 * @author zhailz
 * 2019年2月25日 下午10:15:18
 */
public class L53MaximumSubarray {

	/***
	 
	Given an integer array nums, find the contiguous subarray (containing at least one number) which 
	 has the largest sum and return its sum.
	
	Example:
	
	Input: [-2,1,-3,4,-1,2,1,-5,4],
	
	Output: 6
	
	Explanation: [4,-1,2,1] has the largest sum = 6.
	
	Follow up:
	
	If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, 
	which is more subtle.
	
	 * 
	 * */
	public int maxSubArray(int[] nums) {
		int sumTillNow = 0, max = Integer.MIN_VALUE;

		for (int n : nums) {
			if (n > (sumTillNow + n)) {
				sumTillNow = n;
			} else {
				sumTillNow += n;
			}
			max = Math.max(max, sumTillNow);
		}
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
