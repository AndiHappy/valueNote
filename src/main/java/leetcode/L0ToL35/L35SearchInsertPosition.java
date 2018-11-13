package leetcode.L0ToL35;

/**
 * @author zhailz
 * 2018年11月13日 下午3:56:47
 */
public class L35SearchInsertPosition {

	/**
	
	Given a sorted array and a target value, 
	return the index if the target is found.
	If not, return the index where it would be if it were inserted in order.
	You may assume no duplicates in the array.
	Example 1:
	Input: [1,3,5,6], 5
	Output: 2
	Example 2:
	Input: [1,3,5,6], 2
	Output: 1
	Example 3:
	Input: [1,3,5,6], 7
	Output: 4
	Example 4:
	Input: [1,3,5,6], 0
	Output: 0
	 * */

	public static int searchInsert(int[] nums, int target) {
		int from = 0;int end = nums.length-1;
		int mid = (from+end)/2;
		while(from <= end){
			if(nums[mid] == target){
				return mid;
			}
			
			if(nums[mid] < target){
				from=mid+1;
			}
			
			if(nums[mid] > target){
				end=mid-1;
			}
			mid = (from+end)/2;
		}
		return from;
	}

	public static void main(String[] args) {
		System.out.println(searchInsert(new int[]{1,3,5,6}, 5));
		System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
		System.out.println(searchInsert(new int[]{1,3,5,6}, 7));
		System.out.println(searchInsert(new int[]{1,3,5,6}, 0));

	}

}
