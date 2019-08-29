package pp;
import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L189RotatArray {

	/**
	
	Given an array, rotate the array to the right by k steps, where k is non-negative.
	
	Example 1:
	
	Input: [1,2,3,4,5,6,7] and k = 3
	Output: [5,6,7,1,2,3,4]
	Explanation:
	rotate 1 steps to the right: [7,1,2,3,4,5,6]
	rotate 2 steps to the right: [6,7,1,2,3,4,5]
	rotate 3 steps to the right: [5,6,7,1,2,3,4]
	Example 2:
	
	Input: [-1,-100,3,99] and k = 2
	Output: [3,99,-1,-100]
	Explanation: 
	rotate 1 steps to the right: [99,-1,-100,3]
	rotate 2 steps to the right: [3,99,-1,-100]
	Note:
	
	Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
	Could you do it in-place with O(1) extra space?
	
	 */

	public void rotate(int[] nums, int k) {
		int step = k % nums.length;
		if (step > 0) {
			for (int i = 0; i < step; i++) {
				int tmp = nums[nums.length-1];
				for (int j = nums.length-1; j >0; j--) {
					nums[j] = nums[j-1];
				}
				nums[0]=tmp;
			}
			
		}
	}

	public void rotate_in_place(int[] nums, int k) {
    k %= nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
}

public void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
}
	public static void main(String[] args) {
		L189RotatArray test = new L189RotatArray();
		int[] nums = new int[] {1,2,3,4,5,6,7};
		test.rotate(nums, 1);
		System.out.println(Arrays.toString(nums));
		
		nums = new int[] {1,2,3,4,5,6,7};
		test.rotate(nums, 2);
		System.out.println(Arrays.toString(nums));
		
		nums = new int[] {1,2,3,4,5,6,7};
		test.rotate(nums, 3);
		System.out.println(Arrays.toString(nums));
		
		nums = new int[] {1,2,3,4,5,6,7};
		test.rotate(nums, 6);
		System.out.println(Arrays.toString(nums));
	}

}
