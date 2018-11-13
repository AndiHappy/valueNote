package leetcode.L0ToL35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhailzh
 * 
 Given an array nums of n integers and an integer target, are there elements a, b, c, 
 and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
] 
  
 */
public class L18Lettcode4Sum {
	
	public static void main(String[] args) {
		int[] nums = new int[]{1,0,-1,0,-2,2};
		int target = 0;
		System.out.println(fourSum(nums,target));

	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 4) {
			return res;
		}

		Arrays.sort(nums);
		for (int i = 0; i < nums.length-3; i++) {
			for (int j = i+1 ; j< nums.length -2 ; j++) {
				//此处的去重，已经可以完成，去重的位置非常的重要
				if (j > i + 1 && nums[j] == nums[j - 1]) continue;
				int start = i+2;int end = nums.length -1;
				while(start < end){
					if(nums[i]+nums[j]+nums[start]+nums[end] == target){
						res.add(Arrays.asList(nums[i],nums[j],nums[start],nums[end]));
						start++;
						end--;
					}else if(nums[i]+nums[j]+nums[start]+nums[end]< target){
						start++;
					}else{
						end--;
					}
				}
				
			}
			
		}
		return res;
	}
	
	

	public List<List<Integer>> fourSum1(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> l;
		Arrays.sort(nums);
		int len = nums.length;
		for (int i = 0; i < len - 3; i++) {
			// 相同直接返回
			if (i != 0 && nums[i] == nums[i - 1])
				continue;
			// 最小值如果大于target，直接的返回
			if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
				break;
			// 此值和最大的三个值相加，小于target 直接的返回
			if (nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 2] < target)
				continue;

			for (int j = i + 1; j < len - 2; j++) {
				// 相同直接返回
				if (j != i + 1 && nums[j] == nums[j - 1])
					continue;
				// 最小值如果大于target，直接的返回
				if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target)
					break;
				// 此值和最大的三个值相加，小于target 直接的返回
				if (nums[i] + nums[j] + nums[len - 1] + nums[len - 2] < target)
					continue;

				int head = j + 1, end = len - 1;
				while (head < end) {
					int tempt = nums[i] + nums[j] + nums[head] + nums[end];
					if (tempt == target) {
						l = new ArrayList<Integer>();
						l.add(nums[i]);
						l.add(nums[j]);
						l.add(nums[head]);
						l.add(nums[end]);
						list.add(l);
						head++;
						while (head < end && nums[head] == nums[head - 1]) {
							head++;
						}
					} else if (tempt > target)
						end--;
					else
						head++;
				}
			}
		}
		return list;
	}

}
