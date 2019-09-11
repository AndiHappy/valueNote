package leetcode.pp;
import java.util.Arrays;
import java.util.Comparator;

public class L179LargestNumber {
	
	/**
	Given a list of non negative integers, arrange them such that they form the largest number.
	
	Example 1:
	
	Input: [10,2]
	Output: "210"
	Example 2:
	
	Input: [3,30,34,5,9]
	Output: "9534330"
	Note: The result may be very large, so you need to return a string instead of an integer.
	
	
	 * */

	public String largestNumber(int[] nums) {

		if (nums == null || nums.length == 0)
			return "";
		String[] tmp = new String[nums.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = String.valueOf(nums[i]);
		}

		Arrays.sort(tmp, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				String s1 = str1 + str2;
				String s2 = str2 + str1;
				return s2.compareTo(s1);
			}
		});
		if (tmp[tmp.length - 1].equals("0"))
			return "0";

		StringBuilder builder = new StringBuilder();
		for (int i = tmp.length - 1; i >= 0; i--) {
			builder.append(tmp[i]);
		}
		return builder.toString();
	}

	private void sort(int[] nums, int fromindex, int toindex) {
		if (fromindex < toindex) {
			int index = findkey(nums, fromindex, toindex);
			sort(nums, fromindex, index - 1);
			sort(nums, index + 1, toindex);
		}
	}


	private int findkey(int[] nums, int fromindex, int toindex) {
		int key = nums[fromindex];
		int res = fromindex + 1;
		for (int i = fromindex + 1; i < nums.length; i++) {
			// compare的结果， key值小于nums[i]
			int compareValue = compare(key, nums[i]);
			if (compareValue < 0) {
				continue;
			} else {
				swap(res, i, nums);
				res++;
			}
		}

		swap(res - 1, fromindex, nums);
		return res - 1;
	}


	/**
	 * 每一位的比较301 97，9
	 * */
	private int compare(int key, int i) {
		return 0;
	}

	private void swap(int res, int i, int[] nums) {
		int tmp = nums[res];
		nums[res] = nums[i];
		nums[i] = tmp;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 9, 0, 23, 55, 1, 5, 6, 8, 10 };
		L179LargestNumber test = new L179LargestNumber();
		System.out.println(test.largestNumber(nums));
		nums = new int[] { 3, 30, 34, 5, 9 };
		System.out.println(test.largestNumber(nums));
		nums = new int[] { 824, 938, 1399, 5607, 6973, 5703, 9609, 4398, 8247 };
		System.out.println(test.largestNumber(nums));
		nums = new int[] { 121, 12 };
		System.out.println(test.largestNumber(nums));

		nums = new int[] { 12, 121 };
		System.out.println(test.largestNumber(nums));

		nums = new int[] { 9051, 5526, 2264, 5041, 1630, 5906, 6787, 8382, 4662, 4532, 6804, 4710, 4542, 2116, 7219, 8701,
				8308, 957, 8775, 4822, 396, 8995, 8597, 2304, 8902, 830, 8591, 5828, 9642, 7100, 3976, 5565, 5490, 1613, 5731,
				8052, 8985, 2623, 6325, 3723, 5224, 8274, 4787, 6310, 3393, 78, 3288, 7584, 7440, 5752, 351, 4555, 7265, 9959,
				3866, 9854, 2709, 5817, 7272, 43, 1014, 7527, 3946, 4289, 1272, 5213, 710, 1603, 2436, 8823, 5228, 2581, 771,
				3700, 2109, 5638, 3402, 3910, 871, 5441, 6861, 9556, 1089, 4088, 2788, 9632, 6822, 6145, 5137, 236, 683, 2869,
				9525, 8161, 8374, 2439, 6028, 7813, 6406, 7519 };
		System.out.println(test.largestNumber(nums));

		//		System.out.println(test.findkey(nums, 0, nums.length-1));
		//		System.out.println(Arrays.toString(nums));
		//		test.sort(nums, 0, nums.length-1);
		//		System.out.println(Arrays.toString(nums));

	}

}
