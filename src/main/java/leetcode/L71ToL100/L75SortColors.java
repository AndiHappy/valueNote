package leetcode.L71ToL100;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L75SortColors {

	/**
	 * 
	Given an array with n objects colored red, white or blue, 
	sort them in-place so that objects of the same color are adjacent,
	with the colors in the order red, white and blue.
	
	Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
	
	Note: You are not suppose to use the library's sort function for this problem.
	
	Example:
	
	Input: [2,0,2,1,1,0]
	Output: [0,0,1,1,2,2]
	
	Follow up:
	
	A rather straight forward solution is a two-pass algorithm using counting sort.
	
	First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array
	
	with total number of 0's, then 1's and followed by 2's.
	
	Could you come up with a one-pass algorithm using only constant space?
	
	*/

	public void sortColors(int[] nums) {
		int lt = 0, i = 0, gt = nums.length - 1;

		while (i <= gt) {

			if (nums[i] < 1) {
				swap(nums, lt++, i++);
			} else if (nums[i] > 1) {
				swap(nums, i, gt--);
			} else {
				i++;
			}
		}
	}

	public void swap(int[] nums, int i, int j) {
		nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
	}
	
	public void swap_S(int i,int j,int[] nums){
    nums[i] = nums[i]+nums[j]-(nums[j] = nums[i]);
}
	
	public void sortColors_S(int[] nums) {
    int index = 0, gt = nums.length-1, end0 = 0;
    while(index <= gt){
        if(nums[index] < 1){
            swap_S(index,end0,nums);
            index++;
            end0++;
        }else if(nums[index] > 1){
            swap_S(index,gt,nums);
            gt--;
        }else {
        	 index++;
        }
    }
}

	public static void main(String[] args) {
		L75SortColors test = new L75SortColors();
		int[] res = new int[] {2,0,1};
		test.sortColors_S(res);
		System.out.println(Arrays.toString(res));
	}

}
