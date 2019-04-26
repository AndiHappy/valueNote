
package leetcode.L36ToL70;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L66PlusOne {

	/**
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, 
and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
	 */
	
	 public int[] plusOne(int[] digits) {
		 	int carry = 1;
		 	for (int i = digits.length -1 ; i>=0;i--) {
				if(digits[i]+carry < 10) {
					digits[i] = digits[i]+carry;
					carry=0;
					return digits;
				}else {
					digits[i] = (digits[i]+carry)%10;
					carry = 1;
				}
			}
		 	
		 	if(carry > 0) {
		 		int[] dest = new int[digits.length+1];
		 		System.arraycopy(digits, 0, dest, 1, digits.length);
		 		dest[0]=1;
		 		return dest;
		 		
		 	}else {
		 		return digits;
		 	}  
	    }
	
	public static void main(String[] args) {
		L66PlusOne test = new L66PlusOne();
		int[] value = test.plusOne(new int[] {9,9,9});

		System.out.println(Arrays.toString(value));
	}

}
