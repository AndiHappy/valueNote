package leetcode.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L395LongestSubstringwithAtLeastKRepeatingCharacters {

	/**
	 
	Find the length of the longest substring T of a given string 
	(consists of lowercase letters only) such that every character 
	in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
	 */
	
	public  int longestSubstring(String s, int k) {
		
    if (s == null || s.length() == 0) return 0;
    
    char[] chars = new char[26];
    // record the frequency of each character
    for (int i = 0; i < s.length(); i++) chars[s.charAt(i) - 'a'] += 1;
    
   
    boolean flag = true;
    for (int i = 0; i < chars.length; i += 1) {
        if (chars[i] < k && chars[i] > 0) flag = false;
    }
    // 如果全部的i值都不小于k，直接的返回字符串的长度
    // return the length of string if this string is a valid string
    if (flag == true) return s.length();
    
    
     
    int result = 0;
    int start = 0, cur = 0;
    // otherwise we use all the infrequent elements as splits
    while (cur < s.length()) {
    	// 如果是大于k值的直接的增加，如果是小于k的值，需要计算后重新来过
        if (chars[s.charAt(cur) - 'a'] < k) {
            result = Math.max(result, longestSubstring(s.substring(start, cur), k));
            start = cur + 1;
        }
        cur++;
    }
    
    result = Math.max(result, longestSubstring(s.substring(start), k));
    return result;
}
	
	
	 public int longestSubstring_force(String s, int k) {
     char[] str = s.toCharArray();
     int[] counts = new int[26];
     int h, i, j, idx, max = 0, unique, noLessThanK;
     
     for (h = 1; h <= 26; h++) {
         Arrays.fill(counts, 0);
         i = 0; 
         j = 0;
         unique = 0;
         noLessThanK = 0;
         while (j < str.length) {
             if (unique <= h) {
                 idx = str[j] - 'a';
                 if (counts[idx] == 0)
                     unique++;
                 counts[idx]++;
                 if (counts[idx] == k)
                     noLessThanK++;
                 j++;
             }
             else {
                 idx = str[i] - 'a';
                 if (counts[idx] == k)
                     noLessThanK--;
                 counts[idx]--;
                 if (counts[idx] == 0)
                     unique--;
                 i++;
             }
             if (unique == h && unique == noLessThanK)
                 max = Math.max(j - i, max);
         }
     }
     
     return max;
 }
	
/**
 The idea is pretty basic, find the point where we should split the string,
  eg, the position of character which total count is <k, then dfs it then find the max.
For Example: bbcddefegaghfh and 2, so we shall dfs on "bb", "ddefeg", "ghfh", since a , c only appears1 for once.

 * **/	
	 
	 public int longestSubstring_DC(String s, int k) {
     if (s == null || s.length() == 0 || k == 0) return 0;
     int[] count = new int[26];
     int res = 0;
     for (int i = 0; i < s.length(); i++) {
         count[s.charAt(i) - 'a']++;
     }
     List<Integer> pos = new ArrayList<Integer>();
     for (int i = 0; i < s.length(); i++) {
         if (count[s.charAt(i) - 'a'] < k) pos.add(i);
     }
     if (pos.size() == 0) return s.length();
     
     pos.add(0, -1);
     pos.add(s.length());
     for (int i = 1; i < pos.size(); i++) {
         int start = pos.get(i-1) + 1;
         int end = pos.get(i);
         int next = longestSubstring(s.substring(start, end), k);
         res = Math.max(res, next);
     }
     
     return res;
 }
	
	public static void main(String[] args) {
		/**
		 * "bbaaacbd" 3
		 * */ 

		L395LongestSubstringwithAtLeastKRepeatingCharacters test = new L395LongestSubstringwithAtLeastKRepeatingCharacters();
		int vau = test.longestSubstring("bbccddefegaghfh", 2);
		System.out.println(vau);
	}

}
