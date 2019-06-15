package leetcode.L;

/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 * **/
public class L300LongestIncreasingSubsequence {

	public int lengthOfLIS_DP(int[] nums) {
		// Base case
		if (nums.length <= 1)
			return nums.length;

		// This will be our array to track longest sequence length
		int T[] = new int[nums.length];

		// Fill each position with value 1 in the array
		for (int i = 0; i < nums.length; i++)
			T[i] = 1;


		// Mark one pointer at i. For each i, start from j=0.
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				// It means next number contributes to increasing sequence.
				if (nums[j] < nums[i]) {
					// But increase the value only if it results in a larger value of the sequence than T[i]
					// It is possible that T[i] already has larger value from some previous j'th iteration
					if (T[j] + 1 > T[i]) {
						T[i] = T[j] + 1;
					}
				}
			}
		}

		// Find the maximum length from the array that we just generated 
		int longest = 0;
		for (int i = 0; i < T.length; i++)
			longest = Math.max(longest, T[i]);

		return longest;
	}


	public static int findPositionToReplace(int[] a, int low, int high, int x) {
		int mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (a[mid] == x)
				return mid;
			else if (a[mid] > x)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}

	/**
	 * 
	 * 结论是这个：维护一个上升数组LIS，遍历nums,当出现的数大于这个数组直接append，否则替换掉数组中大于这个数的最小值
    最后LIS的长度就是最长上升子序列的长度
    
    最关键的在于，是怎么推倒出来这个结论的。
    
    
	 * 首先还是梳理问题的特性：
	 * 一组数字，a1，a2,......an
	 * 最大的增长序列。 如果 
	 * ak < ak+1 那么 可以暂时的设置为归类为增长的序列
	 * 
	 * 如果
	 * ak > ak+1 
	 * */
	public static int lengthOfLIS(int[] nums) {
		if (nums == null | nums.length == 0)
			return 0;
		int n = nums.length, len = 0;
		int[] increasingSequence = new int[n];
		increasingSequence[len++] = nums[0];
		for (int i = 1; i < n; i++) {
			int tmpi = nums[i];
			if ( tmpi> increasingSequence[len - 1])
				increasingSequence[len++] = tmpi;
			else {
				int position = findPositionToReplace(increasingSequence, 0, len - 1, tmpi);
				increasingSequence[position] = tmpi;
			}
		}
		return len;
	}
	
	public static void main(String[] args) {
		L300LongestIncreasingSubsequence.lengthOfLIS(new int[]{10,11,2,15,5,16,3,7,101,18});
	}
}

