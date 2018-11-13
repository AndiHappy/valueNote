package leetcode.L0ToL35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import leetcode.base.SignalLikedList;

/**
 * @author zhailz
 *
 * @version 2018年7月11日 上午11:51:21
 * 
 	26. Remove Duplicates from Sorted Array
	
	Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
	
	Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
	
	Example 1:
	
	Given nums = [1,1,2],
	
	Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
	
	It doesn't matter what you leave beyond the returned length.
	Example 2:
	
	Given nums = [0,0,1,1,1,2,2,3,3,4],
	
	Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
	
	It doesn't matter what values are set beyond the returned length.
	Clarification:
	
	Confused why the returned value is an integer but your answer is an array?
	
	Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
	
	Internally you can think of this:
	
	// nums is passed in by reference. (i.e., without making a copy)
	int len = removeDuplicates(nums);
	
	// any modification to nums in your function would be known by the caller.
	// using the length returned by your function, it prints the first len elements.
	for (int i = 0; i < len; i++) {
	print(nums[i]);
	}
 */
public class L26RemoveDuplicatesfromSortedArray {
	public static void main(String[] args) {
		
		L26RemoveDuplicatesfromSortedArray test = new L26RemoveDuplicatesfromSortedArray();
		int[] nums = new int[]{1,1,1,2,3,4,5,6,7,7,7,8};
		int length = test.removeDuplicates(nums);
		System.out.println("length: "+ length + " nums: "+ Arrays.toString(nums));
		
	}


	/*
	 *  去重
	 */
	public int removeDuplicates(int[] nums) {
		if(nums == null || nums.length <2) return nums.length;
		int key = nums[0],cur = 1;
		for (int current = 1; current < nums.length; current++) {
			if(nums[current] != key){
				nums[++cur] = nums[current];
				key = nums[current];
			}
		}
		return cur;
	}

}
