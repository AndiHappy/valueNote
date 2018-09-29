package leetcode;

/**
 * @author zhailz
 *
 * @version 2018年9月28日 下午9:01:03
 * 
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 
 */
public class L28ImplementstrStr {

	/*
	 * 这道题的解法，还可以更加的复杂，做出更加厉害的优化
	 * 但是，我们还是需要研究一个String.indexof() 的JDK的API的源码
	 * */
	public int strStr(String haystack, String needle) {
		if (needle == null || "".equals(needle))
			return 0;
		char[] haystackc = haystack.toCharArray();
		char[] neddlec = needle.toCharArray();
		for (int i = 0; i < haystackc.length - neddlec.length + 1; i++) {
			char hc = haystackc[i];
			if (hc == neddlec[0]) {
				if (meetCondition(haystackc, i + 1, neddlec, 1)) {
					return i;
				}
			}
		}

		return -1;
	}

	private boolean meetCondition(char[] haystackc, int i, char[] neddlec, int j) {
		for (; j < neddlec.length && i < haystackc.length; j++, i++) {
			if (neddlec[j] != haystackc[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		L28ImplementstrStr test = new L28ImplementstrStr();
		int value = test.strStr("htllo", "ll");
		System.out.println(value);

		int value1 = test.strStr("h0tllo", "o");
		System.out.println(value1);

		int value2 = test.strStr("aaaaa", "bba");
		System.out.println(value2);

	}

}
