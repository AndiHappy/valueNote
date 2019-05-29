package leetcode.L130ToL140;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L132PalindromePartitioningII {

	/**

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.


	 */
	
	
	int minCut(String s) {
    int n = s.length();
    int[] cut = new int[n+1];  
    
    // number of cuts for the first k characters
    // Initialize the 'cut' array: 
    // For a string with n characters s[0, n-1], it needs at most n-1 cut.
    // Therefore, the 'cut' array is initialized as cut[i] = i-1
    for (int i = 0; i <= n; i++) cut[i] = i-1;
    System.out.println(Arrays.toString(cut));
    for (int i = 0; i < n; i++) { //variable 'i' represents the center of the palindrome.
    	
//    		The internal loop variable 'j' represents the 'radius' of the palindrome. Apparently, j <= i is a must.
    	
//    		This palindrome can then be represented as s[i-j, i+j]. 
    	
//    	If this string is indeed a palindrome, 
    	
//    	then one possible value of cut[i+j] is cut[i-j] + 1,
    	
//    	where cut[i-j] corresponds to s[0, i-j-1] and 1 correspond to the palindrome s[i-j, i+j];

//    		When the loops finish, you'll get the answer at cut[s.length]


        for (int j = 0; i-j >= 0 && i+j < n && s.charAt(i-j)==s.charAt(i+j) ; j++) {
        	// odd length palindrome
        	cut[i+j+1] = Math.min(cut[i+j+1],1+cut[i-j]);
          System.out.println(Arrays.toString(cut));

        }

        for (int j = 1; i-j+1 >= 0 && i+j < n && s.charAt(i-j+1) == s.charAt(i+j); j++) {
        	// even length palindrome
        	 cut[i+j+1] = Math.min(cut[i+j+1],1+cut[i-j+1]);
           System.out.println(Arrays.toString(cut));
        }
           
    }
    return cut[n];
}
	
	public static void main(String[] args) {
		L132PalindromePartitioningII test = new L132PalindromePartitioningII();
		test.minCut("aabbcd");

	}

}
