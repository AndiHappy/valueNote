package leetcode.L0ToL35;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author zhailzh
 * 
 * @Date 201511131:15:37
 * Tags:HashMap,level0
 * 便利一遍，但是遍历的过程中找到设置hashMap，寻找的时候在hashMap中寻找
 */
public class L01TwoSum {

	public static void main(String[] args) {
		int[] value = new int[]{3,2,4};
		L01TwoSum sum = new L01TwoSum();
		System.out.println(Arrays.toString(sum.twoSum(value, 6)));

	}
	
	 public int[] twoSum_simple(int[] nums, int target) {
	        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
	        int[] res = new int[2];
	        for (int i = 0; i < nums.length; ++i) {
	            if (m.containsKey(target - nums[i])) {
	                res[0] = i;
	                res[1] = m.get(target - nums[i]);
	                break;
	            }
	            m.put(nums[i], i);
	        }
	        return res;
	    }
		
	 
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int value = target - nums[i];
			if(temp.containsKey(value)){
				int index = temp.get(value);
				int[] res = new int[2];
				res[0] = i > index? index:i;
				res[1] = i > index? i:index;
				return res;
			}
			temp.put(nums[i], i);
		}
		return null;
	}
	
	
	//ݽṹˣ
	public int[] twoSum1(int[] nums, int target) {
		int index1 = -1, index2 = -1;
		for (int i = 0; i < nums.length ; i++) {
			index1 = i;
			index2 = getOtherIndex(target-nums[i], nums,index1);
			if(index2 != -1){
				int[] result = new int[2];
				result[0] = index1+1;
				result[1] = index2+1;
				return result;
			}
		}
		return null;
	}

	private int getOtherIndex(int value, int[] nums, int index1) {
		for (int i = 0; i <nums.length; i++) {
			if(nums[i] == value && i != index1){
				return i;
			}
		}
		
		return -1;
	}
}
