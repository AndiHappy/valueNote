package baseAlg;

import java.util.Arrays;

public class Util {
	
	/**
	 * 查找升序的数组中中，包含某一个值
	 * @return 如果包含则返回对应的索引值，如果 不包含则返回-1
	 * */
	public static int  sortArraySearchKey(int[] is, int target) {
		if(is == null || is.length == 0) return -1;
		 int from = 0; int to = is.length-1;
		 while(from <= to) {
			 int mid = (from + to)/2;
			 if(is[mid] == target) return mid;
			 if(is[mid] > target) to = mid-1;
			 if(is[mid] < target) from = mid+1;
		 }
		return -1;
	}
	
	/**
	 * 查找升序的数组中中，是否包含某一个值
	 * @return 是否包含
	 * */
	public static boolean  sortArrayHasKey(int[] is, int target) {
		return sortArraySearchKey(is, target) >= 0;
	}
	
	/**
	 * 交换数组下标的对应的值
	 * */
	public static void swap(int[] nums, int i, int j) {
		nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
	}
	
	public static void main(String[] args) {
		int[] value = new int[] {1,9};
		System.out.println(Arrays.toString(value));

		swap(value, 0, 1);
		System.out.println(Arrays.toString(value));
	}

}
