package leetcode.pp;
import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L169MajorityElement {

	/**

Given an array of size n, find the majority element. The majority element is the element that 
appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2

	 */
	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
    return nums[nums.length/2];
  }
	
	// 摩尔投票法
	public int majorityElement_vote(int[] nums) {
    int ret = nums[0];
    int count = 1;
    for(int num : nums) {
        if(num != ret) {
            count--;
            if(count == 0) {
                count = 1;
                ret = num;
            }
        }
        else
            count++;
    }
    return ret;
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
