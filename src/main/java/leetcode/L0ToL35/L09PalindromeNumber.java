package leetcode.L0ToL35;

/**
 * @author zhailzh
 * 
 * @Date 20151237:19:03
 * 
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Coud you solve it without converting the integer to a string?




 */
public class L09PalindromeNumber {

	public static void main(String[] args) {
		System.out.println(isPalindromeNumber(121));

	}

	public boolean isPalindrome(int num) {
		if (num < 0) {
			return false;
		}
		int sum = num;
		int temp = 0;
		while (sum != 0) {
			temp = temp * 10 + sum % 10;
			sum = sum / 10;
		}
		return temp == num;
	}

	public static boolean isPalindromeNumber(int num) {
		int sum = num;
		int temp = 0;
		while (sum != 0) {
			temp = temp * 10 + sum % 10;
			sum = sum / 10;
		}
		return temp == num;
	}

}
