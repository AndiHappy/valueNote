package baseAlg.dp;

import java.util.Arrays;

/**
 * @author zhailz
 * 2018年12月12日 上午10:10:05
 */
public class L87ScrambleString {

	/**
	
	Given a string s1, we may represent it as a binary tree by 
	partitioning it to two non-empty substrings recursively.
	
	Below is one possible representation of s1 = "great":
	
	great
	/    \
	gr    eat
	/ \    /  \
	g   r  e   at
	       / \
	      a   t
	      
	To scramble the string, we may choose any non-leaf node and swap its two children.
	
	For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
	
	rgeat
	/    \
	rg    eat
	/ \    /  \
	r   g  e   at
	       / \
	      a   t
	      
	We say that "rgeat" is a scrambled string of "great".
	
	
	Similarly, if we continue to swap the children of nodes "eat" and "at", 
	it produces a scrambled string "rgtae".
	
	rgtae
	/    \
	rg    tae
	/ \    /  \
	r   g  ta  e
	   / \
	  t   a
	We say that "rgtae" is a scrambled string of "great".
	
	Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
	
	Example 1:
	
	Input: s1 = "great", s2 = "rgeat"
	Output: true
	Example 2:
	
	Input: s1 = "abcde", s2 = "caebd"
	Output: false
	
	 * */

	/**
	 * DP，首先要有子问题，我们可以假设dp[i][j] 为 s1[0:i] 和 s2[0:j] 是否是Scramble
	
	dp[i][j][1] indiates whether s1(i) equals to s2(j) and third dimension represents length.
	
	dp[i][j][k] indicates whether s1(i, i+k) can be changed from s2(j, j+k).
	
	if dp[i][j][l] and dp[i+l][j+l][k-l] are true, dp[i][j][k] is true. 
	You can understand as which s1(i, i+l) and s2(j, j+l) is scramble 
	
	and s1(i+l, i+k) and s2(j+l, j+k) is scramble, so s1(i, i+k) and s2(j, j+k) is scramble.
	There is same argument. if dp[i][j+k-l][l] and dp[i+l][j][k-l] are true, dp[i][j][k] is true.
	
	 
	 * */

	public boolean isScramble(String s1, String s2) {

		if (s1 == null || s2 == null || s1.length() != s2.length()){
			return false;
		}
			

		if (s1.equals(s2)){
			return true;
		}
			
		char a1[], a2[];

		a1 = s1.toCharArray();
		a2 = s2.toCharArray();
		Arrays.sort(a1);
		Arrays.sort(a2);

		//排序之后，确定字符是一样的
		if (!(new String(a1).equals(new String(a2)))){
			return false;
		}
			
		for (int i = 1; i < s1.length(); i++) {
			
			if (isScramble(s1.substring(0, i), s2.substring(0, i)) 
					&& isScramble(s1.substring(i), s2.substring(i))){
				return true;
			}
				
			if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
					&& isScramble(s1.substring(i), s2.substring(0, s2.length() - i))){
				return true;
			}
				
		}
		return false;
	}

	public static void main(String[] args) {
		L87ScrambleString test = new L87ScrambleString();
		test.isScramble("great", "rgeat");

	}

}
