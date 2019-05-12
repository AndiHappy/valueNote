package leetcode.L71ToL100;

/**
 * @author guizhai
 *
 */
public class L81SearchinRotatedSortedArrayII {

	/**
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?

	 */
	
	/**
假设数组中，左边一部分的连续子数组称为左部，右边一部分的连续子数组称为右部，
左部第一个数字为 start ，则判断某一个数字 mid 在左部还是右部的规则如下：

mid > start，此时在左部
mid < start，此时在右部
mid == start，此时可能在左部也可能在右部
然后就是 mid 的位置与下一步要判断的范围：

mid 在左部
mid > target，target 可能在左部也可能在右部
mid < target，target 在左部，但是在 mid 的右边
mid 在右部
mid > target，target 在右部但是在 mid 的左边
mid < target，target 可能在左部也可能在右部

	 * */

	/**
	 * 重点在于左部右部的划分，取决于start 和 mid值得大小，然后根据确定的左边或者右边，再根据target的值来确定逻辑的分化
	 * 
	 * */
public static boolean search(int[] nums, int target) {
    if (nums == null || nums.length == 0) return false;
    return search(nums, target, 0, nums.length-1);
}
private static boolean search(int[] nums, int target, int start, int end) {
    if (start >= end) {
        return nums[start] == target;
    }
    int mid = (start + end) / 2;
    int num = nums[mid];
    if (num > target) { //中间这个值大于target的时候
        if (num > nums[start]) {// 如果中间值大于大于start，可能在左部，也可能在右部
            return search(nums, target, start, mid-1) || search(nums, target, mid+1, end);
        } else if (num == nums[start]) { //如果中间值等于start说明在右部
            return search(nums, target, mid+1, end);
        } else {// 如果中间值小于start值，说明在左部。
            return search(nums, target, start, mid-1);
        }
    } else if (num < target) { // 如果中间值小于target
        if (num > nums[start]) { // 并且中间值大于start说明target在右部
            return search(nums, target, mid+1, end);
        } else { //如果中间值等于或者大于 start，有可能在在右部，也有可能在右部
            return search(nums, target, mid+1, end) || search(nums, target, start, mid-1);
        }
    } else {
        return true;
    }
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
