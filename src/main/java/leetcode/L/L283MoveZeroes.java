/**
 * 
 */
package leetcode.L;

import java.util.Arrays;

import baseAlg.Util;

/**
 * @author zhailiuzhen
 *
 */
public class L283MoveZeroes {

	/**
	 * 
	 * Given an array nums, write a function to move all 0's to the end of it while
	 * maintaining the relative order of the non-zero elements.
	 * 
	 * Example:
	 * 
	 * Input: [0,1,0,3,12] Output: [1,3,12,0,0] Note:
	 * 
	 * You must do this in-place without making a copy of the array. Minimize the
	 * total number of operations.
	 * 
	 */

	/**
	 * 主要审题：maintaining the relative order of the non-zero elements.
	 * */
	public static void moveZeroes_wrong(int[] nums) {
		int lastNot0index = nums.length-1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				while (nums[lastNot0index]==0 && lastNot0index >i ) lastNot0index--;
				nums[i] = nums[lastNot0index];
				nums[lastNot0index]=0;
			}
		}
	}
	
	public static void moveZeroes(int[] nums) {
		int lastNot0index = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				int tmp = nums[i];
				nums[i] = nums[lastNot0index];
				nums[lastNot0index]=tmp;
				lastNot0index++;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[] {0,1,0,3,12};
		L283MoveZeroes.moveZeroes(nums);
		System.out.println(Arrays.toString(nums));
	}

}
