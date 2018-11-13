package leetcode.L0ToL35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhailzh
 * 
 * @Date 201512204:50:24
 * 
 */
public class L15Lettcode3Sum {

	// Given an array S of n integers, are there elements a, b, c in S such that
	// a + b + c = 0? Find all unique triplets in the array which gives the sum
	// of zero.
	//
	// Note:
	// Elements in a triplet (a,b,c) must be in non-descending order. (ie, a  b
	//  c)
	// The solution set must not contain duplicate triplets.
	// For example, given array S = {-1 0 1 2 -1 -4},
	//
	// A solution set is:
	// (-1, 0, 1)
	// (-1, -1, 2)
	//
	/***
	 *
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
				int from = i + 1;
				int end = nums.length - 1;
				int sumt = 0 - nums[i];
				while (from < end) {
					// 
					int temp = nums[from] + nums[end];
					if (sumt == temp) {
						res.add(Arrays.asList(nums[i], nums[from], nums[end]));
						while (from < end && from + 1 < nums.length && nums[from] == nums[from + 1])
							from++;
						while (from < end && end + 1 < nums.length && nums[end] == nums[end + 1])
							end--;
						from++;
						end--;
					} else if (sumt < temp) {
						end--;
					} else {
						from++;
					}
				}
			}
		}
		return res;
	}

	public List<List<Integer>> threeSum1(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> res = new LinkedList<List<Integer>>();
		for (int i = 0; i < num.length - 2; i++) {
			if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
				int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
				while (lo < hi) {
					if (num[lo] + num[hi] == sum) {
						res.add(Arrays.asList(num[i], num[lo], num[hi]));
						while (lo < hi && num[lo] == num[lo + 1])
							lo++;
						while (lo < hi && num[hi] == num[hi - 1])
							hi--;
						lo++;
						hi--;
					} else if (num[lo] + num[hi] < sum)
						lo++;
					else
						hi--;
				}
			}
		}
		return res;
	}
	
	public int[] quikSort(int[] num){
		if(num == null || num.length < 2){
			return num;
		}
		int len = num.length;
		quicksort(num,0,len-1);
		
		
		return num;
	}

	private void quicksort(int[] num, int start, int end) {
		if(start < end){
			int index = quickindex(num,start,end);
			quicksort(num, start, index-1);
			quicksort(num, index+1, end);
		}
		
	}

	private int quickindex(int[] num, int start, int end) {
		int key = num[start];
		int change = start+1;
		for (int i = start+1; i <= end; i++) {
			if(num[i] < key){
				swap(num,i,change);
				change++;
			}
		}
		swap(num, start, change-1);
		return change - 1;
	}

	private void swap(int[] num, int i, int change) {
		int tmp = num[i];
		num[i] = num[change];
		num[change] = tmp;
	}

	public static void main(String[] args) {
		L15Lettcode3Sum sm = new L15Lettcode3Sum();
		int[] nums = new int[] {4,3,2,5,6,0 };
//		System.out.println(sm.quickindex(nums, 0, nums.length-1));
		System.out.println(Arrays.toString((sm.quikSort(nums))));
		List<List<Integer>> res = sm.threeSum(nums);
		System.out.println(res.toString());
	}
}
