package leetcode.L0ToL35;

import java.util.Arrays;

/**
 * @author zhailz
 * 2018年10月17日 下午7:48:49
 */
public class L34FindFirstandLastPositionofElementinSortedArray {
/*

Given an array of integers nums sorted in ascending order,
find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,8,10], target = 6
Output: [-1,-1]
 
 */
	public int[] searchRange(int[] nums, int target) {
        int from = 0;int end = nums.length-1;
        int bf = -1,ef = -1;
        int mid = (from+end)/2;
        while(from <= end){
        	if(nums[mid] == target){
        		if(nums[from] != target){
        			from++;
        		}
        		
        		if(nums[end] != target){
        			end --;
        		}
        		
        		if(nums[from] == target &&nums[end] == target){
        			bf=from;ef=end;
        			break;
        		}
        	}else if(nums[mid] > target){
        		end = mid-1;
        	}else{
        		from = mid+1;
        	}
        	 mid = (from+end)/2;
        }
        
        return new int[]{bf,ef};
    }
	
	
	public int[] searchRange_addtionalValue(int[] nums, int target) {
        if (nums.length <= 0) {
              return new int[]{-1, -1};
          }

          int start = binarySarch(nums, target - 0.5f, 0, nums.length);
          int end = binarySarch(nums, target + 0.5f, 0, nums.length);

          if (start < nums.length && end <= nums.length) {
              if (nums[start] == target && nums[end - 1] == target) {
                  return new int[]{start, end - 1};
              }
          }

          return new int[]{-1, -1};
      }

      private int binarySarch(int[] nums, float target, int left, int right) {

          if (right <= left) {
              left = left >= nums.length ? nums.length - 1 : left;
              return target < nums[left] ? left : left + 1;
          }

          int mid = (left + right) / 2;
          if (target < nums[mid]) {
              return binarySarch(nums, target, left, mid - 1);
          } else if (target > nums[mid]) {
              return binarySarch(nums, target, mid + 1, right);
          } else {
              return mid;
          }
      }

	public static void main(String[] args) {
		L34FindFirstandLastPositionofElementinSortedArray test = new L34FindFirstandLastPositionofElementinSortedArray();
		System.out.println(Arrays.toString(test.searchRange(new int[]{5,7,7,8,8,8,8,10}, 6)));
		System.out.println(Arrays.toString(test.searchRange(new int[]{5,7,7,8,8,8,8,10}, 8)));

	}

}
