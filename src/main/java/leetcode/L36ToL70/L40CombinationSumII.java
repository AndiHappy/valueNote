package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhailz
 * 2018年11月19日 下午8:48:17
 */
public class L40CombinationSumII {
/**
 Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 * */
	
	public List<List<Integer>> combinationSum2(int[] nums, int target) {
	    List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);
	    backtrack(list, new ArrayList<>(), nums, target, 0);
	    return list;
	    
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
	    if(remain < 0) return;
	    else if(remain == 0) list.add(new ArrayList<>(tempList));
	    else{
	        for(int i = start; i < nums.length; i++){
	        	//“减掉”分支的作用，相同的元素，直接的跳过
	            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
	            tempList.add(nums[i]);
	            backtrack(list, tempList, nums, remain - nums[i], i + 1); //不在重复的
	            tempList.remove(tempList.size() - 1); 
	        }
	    }
	} 
	
	public static void main(String[] args) {
		L40CombinationSumII test = new L40CombinationSumII();
		System.out.println(test.combinationSum2(new int[]{2,5,2,1,2}, 5));

	}

}
