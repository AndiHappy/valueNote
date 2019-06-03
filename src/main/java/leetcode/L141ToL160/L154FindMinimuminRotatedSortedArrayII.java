package leetcode.L141ToL160;

/**
 * @author guizhai
 *
 */
public class L154FindMinimuminRotatedSortedArrayII {

	/**

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?


	 */
	
	public int findMin(int[] nums) {
    
    int lo = 0, hi = nums.length - 1;
//    7,7,7，6,0,1，2，3，4，5
    while (lo < hi) {
    	
    		//需要学习的一个点
        int mi = lo + (hi - lo) / 2;
        
        if (nums[mi] > nums[hi]) { //中间值大于 终点值 
            lo = mi + 1;
        }
        else if (nums[mi] < nums[lo]) {  // 中间值 小于 开始的值
            hi = mi;
            lo++;
        }
        else { // nums[lo] <= nums[mi] <= nums[hi] 
            hi--;
        }
        
    }
    
    return nums[lo];
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
