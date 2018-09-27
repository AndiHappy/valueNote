package leetcode;

/**
 * @author zhailzh
 * 
 * @Date 201512189:43:50
 
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.



 * 
 */
public class L14LongestCommonPrefix {

	public String longestCommonPrefix(String[] strs) {
		
		if (strs == null || strs.length < 1) {
			return "";
		}

		if (strs.length == 1){
			return strs[0];
		}
		
			
		String temp = strs[0];
		boolean flag = true;
		int i = 0;
		for (; i < temp.length() && flag; i++) {
			char tempc = temp.charAt(i);
			for (int j = 1; j < strs.length; j++) {
				boolean ff = i < strs[j].length() && strs[j].charAt(i) == tempc;
				if (!ff) {
					flag = false;
					i = i - 1;
					break;
				}
			}
		}

		return temp.substring(0, i);
	}

	public static void main(String[] args) {
		L14LongestCommonPrefix fix = new L14LongestCommonPrefix();
		fix.longestCommonPrefix(new String[] { "a", "a", "b" });
	}

}
