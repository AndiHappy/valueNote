/**
 * 
 */
package leetcode.L36ToL70;

/**
 * @author guizhai
 *
 */
public class L69Sqrt_x {

	/**
	
	Implement int sqrt(int x).
	
	Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
	
	Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
	
	Example 1:
	
	Input: 4
	Output: 2
	Example 2:
	
	Input: 8
	Output: 2
	Explanation: The square root of 8 is 2.82842..., and since 
	           the decimal part is truncated, 2 is returned.
	 */

	//例如的技巧是 如果结果值为i,则需要满足（i*i <= x && (i+1)*(i+1) > x）
	public int mySqrt(int x) {
		if (x == 0)
			return 0;
		int start = 1, end = x;
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (mid <= x / mid && (mid + 1) > x / (mid + 1))// Found the result
				return mid;
			else if (mid > x / mid)// Keep checking the left part
				end = mid;
			else
				start = mid + 1;// Keep checking the right part
		}
		return start;
	}


	public int mySqrt_2(int x) {
		if (x == 0)
			return 0;
		if (x <= 3)
			return 1;
		int start = 1, end = x, resu = 1;
		while (start <= (end / 2 + 1)) {
			if (start  > end/start) {
				return resu;
			} else if (start == end/start) {
				return start;
			} else {
				resu = start;
				start++;
			}
			
		}
		return start;
	}

	public static void main(String[] args) {
		L69Sqrt_x te = new L69Sqrt_x();
		
		System.out.println(te.mySqrt_2(2147483647));
		
		System.out.println(te.mySqrt_2(8));
		System.out.println(te.mySqrt_2(10));
		System.out.println(te.mySqrt_2(15));
		System.out.println(te.mySqrt_2(16));
	}

}
