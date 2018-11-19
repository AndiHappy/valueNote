package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhailz
 * 2018年11月19日 上午11:27:25
 */
public class L39CombinationSum {
	/*
	Given a set of candidate numbers (candidates) (without duplicates) 
	and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
	
	The same repeated number may be chosen from candidates unlimited number of times.
	
	Note:
	
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.
	Example 1:
	
	Input: candidates = [2,3,6,7], target = 7,
	A solution set is:
	[
	  [7],
	  [2,2,3]
	]
	Example 2:
	
	Input: candidates = [2,3,5], target = 8,
	A solution set is:
	[
	  [2,2,2,2],
	  [2,3,3],
	  [3,5]
	]
	 * 
	 * **/

	public List<List<Integer>> combinationSum_error(int[] candidates, int target) {
		List<Integer> tmp = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
		combinationSum(candidates, target, tmp,res);
		return res;
	}

	private void combinationSum(int[] candidates, int resvalue, List<Integer> tmp,List<List<Integer>> res) {
		System.out.println("resvalue: "+ resvalue + " tmp:" +Arrays.toString(tmp.toArray()));
		for (int canditate : candidates) {
			if (resvalue >= canditate) {
				int restmp = resvalue - canditate;
				if (restmp == 0) {
					List<Integer> tmplist = new ArrayList<>(tmp);
					tmplist.add(canditate);
					res.add(tmplist);
				} else if (resvalue > 0) {
					tmp.add(canditate);
					combinationSum(candidates, restmp, tmp,res);
					tmp.remove(tmp.size()-1);
				}
			}
		}
	}
	
	public List<List<Integer>> combinationSum(int[] nums, int target) {
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
	            tempList.add(nums[i]);
	            backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
	            tempList.remove(tempList.size() - 1);
	        }
	    }
	}

	public static void main(String[] args) {
		L39CombinationSum test = new L39CombinationSum();
		List<List<Integer>> res = test.combinationSum(new int[] {2,3,5}, 8);
		System.out.println(res);
	}

}
