package leetcode.L141ToL160;

/**
 * @author guizhai
 *
 */
public class L153FindMinimuminRotatedSortedArray {

	/**
	
	Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
	
	(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
	
	Find the minimum element.
	
	You may assume no duplicate exists in the array.
	
	Example 1:
	
	Input: [3,4,5,1,2] 
	Output: 1
	Example 2:
	
	Input: [4,5,6,7,0,1,2]
	Output: 0
	 *
	 */


	public int findMin(int[] num) {
		if (num == null || num.length == 0) {
			return 0;
		}

		if (num.length == 1) {
			return num[0];
		}

		int start = 0, end = num.length - 1;
		while (start < end) {
			int mid = (start + end) / 2;

			// 最关键的两个判断
			if (mid > 0 && num[mid] < num[mid - 1]) {
				return num[mid];
			}

			// 如果开始的值 <= mid 在，并且 mid值大于 end值
			if (num[start] <= num[mid] && num[mid] > num[end]) {
				start = mid + 1;
			} else {  // num[mid] < num[start] 或者 num[mid] < num[end] 中间值小于开始的值，这个在后半段。中间值小于end值，这个也在后半段
				end = mid - 1;
			}
		}
		return num[start];
	}

	public static void main(String[] args) {
		L153FindMinimuminRotatedSortedArray test = new L153FindMinimuminRotatedSortedArray();
		test.findMin(new int[] { 4, 5, 6, 7, 0, 1, 2 });
	}

}
