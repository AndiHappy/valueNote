package leetcode.L71ToL90;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L87ScrambleString {

	/**
	
	
	Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
	
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
	
	Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
	
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
	
	 */

	/**
	 * 1，发现是Scramble的规律，判断一个树的两个的子节点
	 * 2，递归迭代
	 * 
	 * */
	public boolean isScramble(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length())
			return false;
		if (s1.length() == 1 && s1.equals(s2))
			return true;
		if (s1.equals(s2))
			return true;
		char[] s1char = s1.toCharArray();
		char[] s2char = s2.toCharArray();
		Arrays.sort(s1char);
		Arrays.sort(s2char);
		if (!new String(s1char).equals(new String(s2char))) {
			return false;
		}

		for (int spit = 1; spit < s1.length(); spit++) {
			String s1left = s1.substring(0, spit);
			String s1right = s1.substring(spit, s1.length());
			if ((isScramble(s1left, s2.substring(0, spit)) && isScramble(s1right, s2.substring(spit, s2.length())))
					|| (isScramble(s1left, s2.substring(s2.length() - spit, s2.length()))
							&& isScramble(s1right, s2.substring(0, s2.length() - spit)))) {
				return true;
			}
		}
		return false;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
