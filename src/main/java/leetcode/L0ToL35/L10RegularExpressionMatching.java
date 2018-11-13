package leetcode.L0ToL35;

/**
 * @author zhailzh
 * 
 * @Date 20151284:11:06
 
 Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false

 
 
 */
public class L10RegularExpressionMatching {

	// Implement regular expression matching with support for '.' and '*'.
	//
	// '.' Matches any single character.
	// '*' Matches zero or more of the preceding element.
	//
	// The matching should cover the entire input string (not partial).
	//
	// The function prototype should be:
	// bool isMatch(const char *s, const char *p)
	//
	// Some examples:
	// isMatch("aa","a")  false
	// isMatch("aa","aa")  true
	// isMatch("aaa","aa")  false
	// isMatch("aa", "a*")  true
	// isMatch("aa", ".*")  true
	// isMatch("ab", ".*")  true
	// isMatch("aab", "c*a*b")  true

	/*
	
		两种解题的思路，一种是DP，一种是递归的解法，首先是DP
		dp[i][j]的含义是s[0-i] 与 p[0-j]是否匹配。
		那么dp[i][0]标识为p为Null，s[0]标识s为NULL
		
		
		p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]
			
				If p.charAt(j) == ‘.’ : dp[i][j] = dp[i-1][j-1];
				
				
				If p.charAt(j) == ‘*’: 	
				here are two sub conditions: 
				if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty
				if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == ‘.’: 
				
				dp[i][j] = dp[i-1][j] //in this case, a* counts as multiple a 
				dp[i][j] = dp[i][j-1] // in this case, a* counts as single a 
				dp[i][j] = dp[i][j-2] // in this case, a* counts as empty
	*/

	public static boolean isMatch1(String s, String p) {
		int m = s.length(), n = p.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		//初始化第0行,除了[0][0]全为false，毋庸置疑，因为空串p只能匹配空串，其他都无能匹配
		for (int i = 1; i <= m; i++)
			dp[i][0] = false;
		//初始化第0列，只有X*能匹配空串，如果有*，它的真值一定和p[0][j-2]的相同（略过它之前的符号）
		for (int j = 1; j <= n; j++)
			dp[0][j] = j > 1 && '*' == p.charAt(j - 1) && dp[0][j - 2];

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				//由于表格中是从1开始的，而字符串中是以0开始的，所以i-1和j-1才对应字符串中的字符。
				if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i][j - 2]
							|| (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j];

				} else //只有当前字符完全匹配，才有资格传递dp[i-1][j-1] 真值
				{
					dp[i][j] = (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1)) && dp[i - 1][j - 1];

				}
			}
		}
		return dp[m][n];
	}

	public static boolean isMatch(String s, String p) {
		if (s == null && p == null)
			return true;
		if (s == null || p == null)
			return false;
		return isMatch(s, 0, p, 0);
	}

	/**
	 * 递归的调用的方式
	 * */
	public static boolean isMatch(String s, int sstart, String p, int pstart) {
		if (s.length() == sstart && p.length() == pstart)
			return true;
		if (p.length() == pstart)
			return false;
		if (s.length() == sstart) {

			if (pstart + 1 >= p.length() || p.charAt(pstart + 1) != '*') {
				return false;
			}
			return isMatch(s, sstart, p, pstart + 2);
		}

		if (pstart + 1 <= p.length() - 1 && p.charAt(pstart + 1) == '*') {
			if (s.charAt(sstart) == p.charAt(pstart) || p.charAt(pstart) == '.') {
				return isMatch(s, sstart + 1, p, pstart) || isMatch(s, sstart, p, pstart + 2);
			}
			return isMatch(s, sstart, p, pstart + 2);
		} else if (p.charAt(pstart) == '.') {
			return isMatch(s, sstart + 1, p, pstart + 1);
		} else {
			return p.charAt(pstart) == s.charAt(sstart) && isMatch(s, sstart + 1, p, pstart + 1);
		}
	}

	public static void main(String[] args) {
		L10RegularExpressionMatching na = new L10RegularExpressionMatching();
		boolean result = na.isMatch("asdaab", "c*a*b") == na.isMatch("asdaab", 0, "c*a*b", 0);
		System.out.println(result);

		boolean result2 = na.isMatch("a", "c") == na.isMatch("a", 0, "c", 0);
		System.out.println(result2);

		boolean result1 = na.isMatch("a", "a*");
		na.isMatch("aaa", 0, "aaa*", 0);
		System.out.println(result1);

		isMatch("aa", "a");
		isMatch("aa", "aa");
		isMatch("aaa", "aa");
		isMatch("aa", "a*");
		isMatch("aa", ".*");
		isMatch("ab", ".*");
		isMatch("aab", "c*a*b");
	}

}
