package leetcode.L36ToL70;

/**
 * @author zhailz
 * 2018年11月28日 下午10:59:36
 */
public class L44WildcardMatching {
	/**
	 
	Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
	
	'?' Matches any single character.
	'*' Matches any sequence of characters (including the empty sequence).
	The matching should cover the entire input string (not partial).
	
	Note:
	
	s could be empty and contains only lowercase letters a-z.
	p could be empty and contains only lowercase letters a-z, and characters like ? or *.
	Example 1:
	
	Input:
	s = "aa"
	p = "a"
	Output: false
	Explanation: "a" does not match the entire string "aa".
	Example 2:
	
	Input:
	s = "aa"
	p = "*"
	Output: true
	Explanation: '*' matches any sequence.
	Example 3:
	
	Input:
	s = "cb"
	p = "?a"
	Output: false
	Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
	Example 4:
	
	Input:
	s = "adceb"
	p = "*a*b"
	Output: true
	Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
	Example 5:
	
	Input:
	s = "acdcb"
	p = "a*c?b"
	Output: false
	
	 * */

	
	

	/*

Hi it really takes me a long time to figure this problem out, 
and I would love to share my notes about this DP solution!

The most confusing part for me is how to deal with '*'. 
At first I couldn't figure out why the condition would be 
(dp[i-1][j] == true || dp[i][j-1] == true). 
Hope detailed DP description below helps!

dp[i][j]: true if the first i char in String s matches the first j chars in String p

Base case:
     
     origin: dp[0][0]: they do match, so dp[0][0] = true,0,0标识的是null元素，还没有开始呢

     first row: dp[0][j]: except for String p starts with *, otherwise all false

     first col: dp[i][0]: can't match when p is empty. All false.
     
Recursion:

Iterate through every dp[i][j]

dp[i][j] = true:

	if (s[ith] == p[jth] || p[jth] == '?') && dp[i-1][j-1] == true 
	
	else if p[jth] == '*' && (dp[i-1][j] == true || dp[i][j-1] == true) 
	
-for dp[i-1][j], means that * acts like an empty sequence. 
eg: ab, ab*
-for dp[i][j-1], means that * acts like any sequences
eg: abcd, ab*

Start from 0 to len
Output put should be dp[s.len][p.len], referring to the whole s matches the whole p
Be careful about the difference of index i,j in String (0 to len-1) and the index i, j in dp (0 to len)!

*/
	public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        
        // Base cases:
        dp[0][0] = true;
        for(int i = 1; i <= sLen; i++){
            dp[i][0] = false;
        }       
        for(int j = 1; j <= pLen; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-1];
            }            
        }
        
        // Recursion:
        for(int i = 1; i <= sLen; i++){
            for(int j = 1; j <= pLen; j++){
                if((s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') && dp[i-1][j-1])
                    dp[i][j] = true;
                else if (p.charAt(j-1) == '*' && (dp[i-1][j] || dp[i][j-1])) // dp[i][j-1]为true，*能够匹配null字符，dp[i-1][j]，表示*能够匹配所有的字符
                    dp[i][j] = true;
            }
        }
        return dp[sLen][pLen];
    }
	
	
	public boolean isMatch_simple(String s, String p) {
        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        dp[0][0]=true;
        for(int i=1;i<=p.length();i++){
            if(p.charAt(i-1) == '*' && dp[i-1][0]){
                dp[i][0]=true;
            }
        }
        for(int i=1;i<=p.length();i++){
             for(int j=1;j<=s.length();j++){
                if(s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?'){
                    dp[i][j]=dp[i-1][j-1];
                }else if(p.charAt(i-1) == '*'){
                    dp[i][j]=dp[i-1][j]||dp[i][j-1];
                }
             } 
        }
        return dp[p.length()][s.length()];
    }
	
	
	/**
	 * 注重思路的推演
	 * 匹配规则，这个是有回退的操作的，是因为匹配规则的不确定，?只能匹配一个，*可以匹配多个字符串，所以首先
	 * 尝试的是回溯的算法结构，简单来说就是回溯的递归调用，
	 * 
	 * 确定每一步的尝试方案，
	 * 如果p的匹配点不是？*的话，直接的匹配，如果匹配成功，则进入下一步，
	 * 如果匹配不成功，则返回false
	 * 然后就是？和* 的情况
	 * 
	 * 回溯匹配s的长度，int from ，p 的匹配的开始
	 * */
	private boolean isMatch_backtracking(String s, int i, String p, int j) {
		//匹配到最后
		if(i==s.length() && j == p.length()) return true;
		//当i匹配到了最后，取决于p剩下的是什么
		if(i>= s.length()){
			if(j< p.length()){
				for (int j2 = j; j2 <  p.length(); j2++) {
					if(p.charAt(j2) != '*') return false;
				}
				return true;
			}
			 return false;
		}
		
		if(j >= p.length() && i < s.length()){
			return false;
		}
		
		char pj = p.charAt(j);
		//第一种情况
		if( pj!= '?' && pj != '*'){
			if(s.charAt(i) == pj) return isMatch_backtracking(s, i+1, p, j+1);
			if(s.charAt(i) != pj) return false;
		}
		
		//第二种情况,直接匹配一个字符
		if(pj == '?'){
			return isMatch_backtracking(s, i+1, p, j+1);
		}
		
		//第三种情况
		if(pj == '*'){
			boolean value = false;
			for (int k = i; k <= s.length(); k++) {
				int k2 = j+1;
				for (;k2 < p.length(); k2++) {
					if(p.charAt(k2) != '*'){
						break;
					}
				}
				value = value | isMatch_backtracking(s, k, p, k2);
				if(value) return value;
			}
			return value;
		}
		return false;
	}

	public static void main(String[] args) {
		L44WildcardMatching test = new L44WildcardMatching();
		System.out.println(test.isMatch("aa", "a"));
		System.out.println(test.isMatch("aa", "aa"));
		System.out.println(test.isMatch("cb", "?a"));
		System.out.println(test.isMatch("adceb", "*a*b"));
		System.out.println(test.isMatch("acdcb", "a*c?b"));
		System.out.println(test.isMatch("", "*"));
		System.out.println(test.isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******b"));
		//"bbabb"
		System.out.println(test.isMatch("bbabb","??**b*"));

		
	}

}
