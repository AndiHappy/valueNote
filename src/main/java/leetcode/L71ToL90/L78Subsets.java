package leetcode.L71ToL90;

import java.util.ArrayList;
import java.util.List;

public class L78Subsets {

	/**
	
	Given a set of distinct integers, nums, return all possible subsets (the power set).
	
	Note: The solution set must not contain duplicate subsets.
	
	Example:
	
	Input: nums = [1,2,3]
	Output:
	[
	[3],
	[1],
	[2],
	[1,2,3],
	[1,3],
	[2,3],
	[1,2],
	[]
	]
	
	 * */


	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> rs = new ArrayList<>();
		backtrack(rs,new ArrayList<Integer>(), nums, 0);
		return rs;
	}


/**
 * 回溯算法最重要的是：
 * */
	private void backtrack(List<List<Integer>> rs,List<Integer> cur, int[] nums, int start) {
		rs.add(new ArrayList<>(cur));
		for (int i = start; i < nums.length; i++) {
			cur.add(nums[i]);
			backtrack(rs,cur, nums, i + 1);
			cur.remove(cur.size() - 1);
		}
	}

	public static void main(String[] args) {
		L78Subsets test = new L78Subsets();
		int[] nums = new int[] { 1, 2, 3 };
		List<List<Integer>> res = test.subsets(nums);
		System.out.println(res);
	}

}
