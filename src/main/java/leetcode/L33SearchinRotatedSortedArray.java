package leetcode;

/**
 * @author zhailz
 * 2018年10月13日 下午2:47:44
 */
public class L33SearchinRotatedSortedArray {

	/**
	Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
	
	(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
	
	You are given a target value to search. If found in the array return its index, otherwise return -1.
	
	You may assume no duplicate exists in the array.
	
	Your algorithm's runtime complexity must be in the order of O(log n).
	
	Example 1:
	
	Input: nums = [4,5,6,7,0,1,2], target = 0
	Output: 4
	Example 2:
	
	Input: nums = [4,5,6,7,0,1,2], target = 3
	Output: -1
	
	 * */
	
	/**
	 * 错误的想法，导致错误的编程
	 * error：打算直接的根据这样的队列，采用二分查找。
	 * right：二分查找的前提是，有序数据。既然数据不是有序的，是扭曲的。考虑的应该是怎么"扭曲"回来.
	 * */
	public static void main(String[] args) {
		int[] nums = new int[] { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println("4 = " + search_error(nums, 0, nums.length - 1, 0));
		System.out.println("1 = " + search_error(nums, 0, nums.length - 1, 5));
		System.out.println("-1 = " + search_error(nums, 0, nums.length - 1, 3));
		nums = new int[] { 1, 3 };
		System.out.println("1 = " + search_error(nums, 0, nums.length - 1, 3));

		nums = new int[] { 5, 1, 3 };
		System.out.println("2 = " + search_error(nums, 0, nums.length - 1, 3));
		nums = new int[] { 3, 1 };
		System.out.println("1 = " + search_error(nums, 0, nums.length - 1, 1));

		nums = new int[] { 1, 3 };
		System.out.println("1 = " + search_error(nums, 0, nums.length - 1, 3));

		nums = new int[] { 3, 0, 1, 2 };
		System.out.println("2 = " + search_error(nums, 0, nums.length - 1, 1));

		nums = new int[] { 4, 5, 6, 7, 8, 1, 2, 3 };
		System.out.println("4 = " + search_error(nums, 0, nums.length - 1, 8));

	}

	public int search(int[] nums, int target) {
		return search(nums, nums.length, target);
	}

	int search(int A[], int n, int target) {
		int lo = 0, hi = n - 1;
		// find the index of the smallest value using binary search.
		// Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
		// Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (A[mid] > A[hi])
				lo = mid + 1;
			else
				hi = mid;
		}
		// lo==hi is the index of the smallest value and also the number of places rotated.
		int rot = lo;
		lo = 0;
		hi = n - 1;
		// The usual binary search and accounting for rotation.
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			int realmid = (mid + rot) % n;
			if (A[realmid] == target)
				return realmid;
			if (A[realmid] < target)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return -1;
	}

	public static int search_error(int[] value, int fromindex, int toindex, int key) {
		if (value.length < 1)
			return -1;
		if (fromindex > toindex)
			return -1;
		//中间值
		int keyindex = (fromindex + toindex) / 2;

		int keyValue = value[keyindex];

		if (keyValue == key) {
			return keyindex;
		} else {
			//正规的快排模式
			if ((keyValue >= value[fromindex] && keyValue <= value[toindex] && value[fromindex] <= value[toindex])) {
				if (keyValue > key) {
					return search_error(value, fromindex, keyindex - 1, key);
				} else {
					return search_error(value, keyindex + 1, toindex, key);
				}
			} else if ((keyValue <= value[fromindex] && keyValue >= value[toindex]
					&& value[fromindex] >= value[toindex])) {
				if (keyValue > key) {
					return search_error(value, keyindex + 1, toindex, key);
				} else {
					return search_error(value, fromindex, keyindex - 1, key);
				}
			} else {
				//非递增或者非递减的模式
				if ((keyValue > key)) {
					if (key >= value[fromindex]) {
						return search_error(value, fromindex, keyindex - 1, key);
					} else {
						return search_error(value, keyindex + 1, toindex, key);
					}

				} else {
					// keyValue<key
					if (key >= value[fromindex]) {
						return search_error(value, fromindex, keyindex - 1, key);
					} else {
						return search_error(value, keyindex + 1, toindex, key);
					}
				}
			}
		}
	}
}
