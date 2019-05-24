package leetcode.L112ToL129;

/**
 * @author guizhai
 *
 */
public class L125ValidPalindrome {

	/**

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false

	 */
	
	public boolean isPalindrome(String s) {
    if (s.isEmpty()) {
    	return true;
    }
    int head = 0, tail = s.length() - 1;
    char cHead, cTail;
    while(head <= tail) {
    	cHead = s.charAt(head);
    	cTail = s.charAt(tail);
    	// 特别注意异常情况的判断
    	if (!Character.isLetterOrDigit(cHead)) {
    		head++;
    	} else if(!Character.isLetterOrDigit(cTail)) {
    		tail--;
    	} else {
    		if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
    			return false;
    		}
    		head++;
    		tail--;
    	}
    }
    
    return true;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
