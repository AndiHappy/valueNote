package leetcode.L0ToL35;

import java.util.Arrays;

/**
 * @author zhailz
 *
 * @version 2018年10月8日 上午11:57:50
 */
public class L31NextPermutation {

/**

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

字典序的定义比较的难以理解，其实就是：
字典序排列就是按照字典a-z,1-9的顺序给出字符串的顺序全排列，例如abc的全排列就是从abc一直排到cba。那么给定一个字符串，怎么找出恰好大于该字符串的下一个排列呢？

*/
	public void nextPermutation(int[] nums) {
		int index = nums.length -1;
        while(index > 0 && nums[index-1] >= nums[index]) index--;
        if(index == 0){
        	Arrays.sort(nums);
        }else{
        	 change(index-1,nums.length -1,nums);
        	 Arrays.sort(nums,index,nums.length);
        }
        System.out.println(Arrays.toString(nums));
    }
	
	private void change(int i, int j, int[] nums) {
		int tmp = nums[i];
		while(nums[j] <= tmp){
			j--;
		}
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	

	public static void main(String[] args) {
		L31NextPermutation test = new L31NextPermutation();
		int[] nums = new int[]{1,1,5,4,3};
//		test.nextPermutation(new int[]{1,2,3});//1,3,2
//		test.nextPermutation(new int[]{3,2,1});//1,2,3
//		test.nextPermutation(new int[]{1,1,5});//1,5,1
//		test.nextPermutation(new int[]{1,3,2});//2,1,3
//		test.nextPermutation(new int[]{2,3,1});//3,1,2
		test.nextPermutation(new int[]{1,5,1});//5,1,1
		test.nextPermutation(nums );

	}

}
