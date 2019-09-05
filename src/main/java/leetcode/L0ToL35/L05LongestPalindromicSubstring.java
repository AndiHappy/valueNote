package leetcode.L0ToL35;

/**
 * @author zhailzh
 * 
 * @Date 201511162:17:16
 * 
 * 
 * 
 */
public class L05LongestPalindromicSubstring {

	public static void main(String[] args) {
		//		String value = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
		L05LongestPalindromicSubstring lo = new L05LongestPalindromicSubstring();
		//		System.out.println(lo.longestPalindrome(value));
		System.out.println(lo.longnestPalindrome("abacabbacb"));
		System.out.println(lo.longestPalindrome_explation("abacabbacb"));
		System.out.println(lo.longestPalindrome("abacabbacb"));
	}


	/**
	 * 理解是其中的关键：回文字符串的判定
	 * */

	public String longnestPalindrome(String s) {
		char[] tmpchar = s.toCharArray();
		int res = 0;
		int start = 0;
		for (int i = 0; i < tmpchar.length - 1; i++) {
			int odd = getLength(tmpchar, i, i);
			int even = getLength(tmpchar, i, i + 1);
			int tmpres = Math.max(odd, even);
			if (tmpres > res) {
				res = tmpres;
				start = i - (res - 1) / 2;
			}
		}
		return s.substring(start, start + res);
	}


	private int getLength(char[] tmpchar, int i, int j) {
		if (tmpchar[i] != tmpchar[j])
			return 1;
		while (i >= 0 && j < tmpchar.length && tmpchar[i] == tmpchar[j]) {
			i--;
			j++;
		}
		return j - i - 1;
	}
	
//	 dp[i][j] indicates whether substring s starting at index i and ending at j is palindrome
//	
	public static String longestPalindrome_explation(String s) {
    int n = s.length();
    int palindromeStartsAt = 0, maxLen = 0;
//   abacabbacb
    boolean[][] dp = new boolean[n][n];
//    for(int i = 0; i <n; i++) { 
    for (int i = n - 1; i >= 0; i--) {
        for(int j = i; j < n; j++) { 
            dp[i][j] = (s.charAt(i) == s.charAt(j)) && ( j-i < 3  || dp[i+1][j-1]); 
            if(dp[i][j] && (j-i+1 > maxLen)) {
                palindromeStartsAt = i;
                maxLen = j-i+1;
            }
        }
    }
    return s.substring(palindromeStartsAt, palindromeStartsAt+maxLen);
}


	//dp(i, j) represents whether s(i ... j) can form a palindromic substring, 
	//	dp(i, j) is true when s(i) equals to s(j) and s(i+1 ... j-1) is a palindromic substring.
	//	When we found a palindrome, check if it's the longest one. 
	//	Time complexity O(n^2).

	public String longestPalindrome(String s) {
		int n = s.length();
		String res = null;

		boolean[][] dp = new boolean[n][n];

		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
				if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
					res = s.substring(i, j + 1);
				}
			}
		}

		return res;
	}

	/**
	 * 
	 * */
	public String longestPalindrome1(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		int n = s.length();
		int max = 0;
		int right = 0;
		int left = 0;
		boolean[][] dp = new boolean[n][n];

		for (int j = 1; j < n; j++) {
			for (int i = 0; i < j; i++) {
				boolean isInnerWordPalindrom = dp[i + 1][j - 1] || (j - i <= 2);
				if (s.charAt(i) == s.charAt(j) && isInnerWordPalindrom) {
					dp[i][j] = true;
					if ((j - i + 1) > max) {
						max = j - i + 1;
						left = i;
						right = j;
					}
				}
			}
		}
		return s.substring(left, right + 1);
	}

	public String longestPalindrome2(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		int n = s.length();
		int max = 0;
		int right = 0;
		int left = 0;
		boolean[][] dp = new boolean[n][n];

		for (int j = 1; j < n; j++) {
			for (int i = 0; i < j; i++) {
				if ((s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) || (s.charAt(i) == s.charAt(j) && (j - i <= 2))) {
					dp[i][j] = true;
					if ((j - i + 1) > max) {
						max = j - i + 1;
						left = i;
						right = j;
					}
				}
			}
		}
		return s.substring(left, right + 1);
	}

	@Deprecated
	public String longestPalindrome_isWrong(String s) {
		int temp = 0;
		int tempi = 0;
		int tempj = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				if (isPalind(s, i, j)) {
					if (temp < (j - i + 1)) {
						temp = j - i + 1;
						tempi = i;
						tempj = j;
					}
				}
			}
		}
		if (temp != 0) {
			return s.substring(tempi, tempj);
		}
		return null;
	}


	public boolean isPalind(String value, int start, int end) {

		if (!value.isEmpty()) {
			while (start <= end) {
				if (value.charAt(start) == value.charAt(end)) {
					start++;
					end--;
				} else {
					return false;
				}
			}
		}
		return true;
	}

}
