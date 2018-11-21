package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhailz
 * 2018年11月19日 上午11:27:25
 */
public class L39CombinationSum {
	public static Logger log = LoggerFactory.getLogger("L39CombinationSum");
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
	
	错误的不是自己的大致的思路，而是思路的清晰度，想的不清楚的情况下，想要非常顺利的编码比较的困难。
	例如在这次递归中，主要控制的量，有两个，一个是递归的过程中满足条件的结果，一个是tmp 和 resvalue 的控制。
	combinationSum_error，结果值已经非常的接近，需要处理的是重复的内容，这个时候，就需要控制递归开始的元素
	添加了控制开始的元素以后，需要注意的是需要先判定一个是tmp 和 resvalue 的控制，然后从开始元素进行递归。
	 * 
	 * **/

	public List<List<Integer>> combinationSum_error(int[] candidates, int target) {
		List<Integer> tmp = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
		combinationSum(candidates, 0, target, tmp, res);
		return res;
	}

	public void combinationSum_error(int[] candidates, int resvalue, List<Integer> tmp, List<List<Integer>> res) {
		System.out.println("resvalue: " + resvalue + " tmp:" + Arrays.toString(tmp.toArray()));
		for (int canditate : candidates) {
			if (resvalue >= canditate) {
				int restmp = resvalue - canditate;
				if (restmp == 0) {
					List<Integer> tmplist = new ArrayList<>(tmp);
					tmplist.add(canditate);
					res.add(tmplist);
				} else if (resvalue > 0) {
					tmp.add(canditate);
					combinationSum_error(candidates, restmp, tmp, res);
					tmp.remove(tmp.size() - 1);
				}
			}
		}
	}

	public void combinationSum_error1(int[] candidates, int from, int resvalue, List<Integer> tmp, List<List<Integer>> res) {
		System.out.println("resvalue: " + resvalue + " tmp:" + Arrays.toString(tmp.toArray()) + "from :" + from);
		for (int i = from; i < candidates.length; i++) {
			int canditate = candidates[from];
			if (resvalue >= canditate) {
				int restmp = resvalue - canditate;
				if (restmp == 0) {
					List<Integer> tmplist = new ArrayList<>(tmp);
					tmplist.add(canditate);
					res.add(tmplist);
				} else if (resvalue > 0) {
					tmp.add(canditate);
					combinationSum(candidates, from+1, restmp, tmp, res);
					tmp.remove(tmp.size() - 1);
				}
			}
		}
	}

	
	private void combinationSum(int[] candidates, int from, int resvalue, List<Integer> tmp, List<List<Integer>> res) {
		System.out.println("resvalue: " + resvalue + " tmp:" + Arrays.toString(tmp.toArray()) + "from :" + from);
		if(resvalue <0) return;
		if(resvalue == 0){
			res.add(new ArrayList<>(tmp));
			return;
		}else{
			for (int i = from; i < candidates.length; i++) {
				tmp.add(candidates[i]);
				//这个地方的from，有了两个选择，一个是from，一个是i，这里的避免重复的开始，例如[2,3,3] 和[3,2,3]的格式
				//应该使用的是i
				combinationSum(candidates, i, resvalue-candidates[i], tmp, res);
				tmp.remove(tmp.size() - 1);
			}
		}
	}
	
	public List<List<Integer>> combinationSum(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, target, 0);
		return list;
	}

	/*
	这几步的递归比较的有意思：
	11:16:08.045 [main] INFO  L39CombinationSum - tempList:[2, 2, 2], remmain:2, start:0
	11:16:22.852 [main] INFO  L39CombinationSum - tempList:[2, 2, 2, 2], remmain:0, start:0
	11:16:53.452 [main] INFO  L39CombinationSum - tempList:[2, 2, 2, 3], remmain:-1, start:1
	11:17:39.233 [main] INFO  L39CombinationSum - tempList:[2, 2, 2, 5], remmain:-3, start:2
	**/
	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
		log.info("tempList:{}, remmain:{}, start:{}", tempList, remain, start);
		if (remain < 0)
			return;
		else if (remain == 0) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = start; i < nums.length; i++) {
				tempList.add(nums[i]);
				backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		L39CombinationSum test = new L39CombinationSum();
		List<List<Integer>> res = test.combinationSum(new int[] { 2, 3, 5 }, 8);
		System.out.println(res);

		List<List<Integer>> res1 = test.combinationSum_error(new int[] { 2, 3, 5 }, 8);
		System.out.println(res1);
	}

}
