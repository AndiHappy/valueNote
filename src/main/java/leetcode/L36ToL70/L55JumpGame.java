package leetcode.L36ToL70;

/**
 * @author zhailz
 * 2019年2月26日 下午7:52:44
 */
public class L55JumpGame {

	/**
	 * 
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
	 * */
	
public boolean canJump(int[] nums) {
	if(nums == null) return false;
	if(nums.length == 1 && nums[0] >=1) return true;
	int max = nums[0]+1;
	for (int i = 0; i < max && i < nums.length; i++) {
		max = Math.max(max,nums[i]+i+1);
		if(max >= nums.length){
			return true;
		}
	}
	
	return false;
}

	public static void main(String[] args) {
		L55JumpGame test = new L55JumpGame();
		System.out.println(test.canJump(new int[]{2,3,1,1,4}));
		System.out.println(test.canJump(new int[]{3,2,1,0,4}));

	}

}
