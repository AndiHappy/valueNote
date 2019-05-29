package leetcode.L130ToL140;

/**
 * @author guizhai
 *
 */
public class L136SingleNumber {

	/**

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4

	 */
	
	// 使用 异或 操作特性，A^A = 0 A^0 = A
	int singleNumber(int A[], int n) {
    int result = 0;
    for (int i = 0; i<n; i++)
    {
		result ^=A[i];
    }
	return result;
}
	
	
	
	public static void main(String[] args) {
		System.out.println(122^0);
		
		System.out.println(122^121^121^0);

	}

}
