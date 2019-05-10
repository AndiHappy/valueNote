package leetcode.L71ToL100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guizhai
 *
 */
public class L76MinimumWindowSubstring {

	/**
	 
Given a string S and a string T, 
find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".

If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

	 */
	 
	public String minWindow(String s, String t) {
    
		int[] count = new int[128];
    
    for(char c : t.toCharArray()) count[c]++;
    
    int min = s.length() + 1, i = 0, j = 0, remain = t.length();
    
    String res = "";
    
    while(i < s.length()) {
    	
        if(--count[s.charAt(i++)] >= 0) remain--;
        
        while(remain == 0) {
            if(i - j < min) {
                min = i - j;
                res = s.substring(j, i);
            }
            
            if(++count[s.charAt(j++)] > 0) remain++;;
        }
    }
    
    return res;
}
	
	public String minWindow_HashMap(String s, String t) {
    Map<Character, Integer> map = new HashMap<>();
    for(char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
    int count = map.size();
    
    int i = 0, j = 0, min = s.length() + 1;
    String res = "";

    while(i < s.length()) {
        char c = s.charAt(i++);
        if(!map.containsKey(c)) continue;
        map.put(c, map.get(c) - 1);
        if(map.get(c) == 0) count--;

        while(count == 0) {
            while(j < i && !map.containsKey(s.charAt(j))) j++;
            if(i-j < min) {
                min = i - j;
                res = s.substring(j, i);
            }
            char c1 = s.charAt(j);
            map.put(c1, map.get(c1) + 1);
            if(map.get(c1) > 0) count++;
            j++;
        }
    }
    return res;
}
	
	/**
	 * 以窗口为例：限定滑动的条件 missing和count的条件
	 * 
	 * */
	
	public String min(String s ,String t) {
		if(s.isEmpty() || t.isEmpty()) return "";
    Map<Character, Integer> need = new HashMap<>();
    t.chars().forEach(e->need.put((char)e, need.getOrDefault((char)e, 0) + 1));
    int i = 0, j = 0, l = 0, r = 0, missing = t.length();
    while(r < s.length()){
        char right = s.charAt(r);
        need.putIfAbsent(right, -1);
        // 这个判定的条件非常的厉害：维护窗口的滑动的条件
        if(need.get(right) > 0) {
        	missing -= 1;
        }
        need.put(right, need.get(right) - 1);
        r += 1;
        while(missing == 0){//窗口的维护，尽量的找到
            if(j == 0 || (r - l) < (j - i)){
                j = r;
                i = l;
            }
            char left = s.charAt(l);
            need.putIfAbsent(left, -1);
            need.put(left, need.get(left) + 1);
            if(need.get(left) > 0) missing += 1;
            l += 1;
        }
    }
    return s.substring(i, j);
	}
	 
	public static void main(String[] args) {
		L76MinimumWindowSubstring test = new L76MinimumWindowSubstring();
		System.out.println(test.min("AAADOBBBECODEBANC", "ABC"));

	}

}
