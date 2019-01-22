package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhailz
 * 2019年1月21日 下午4:21:52
 */
public class L49GroupAnagrams {

	/**
	 Given an array of strings, group anagrams together.
	
	Example:
	
	Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
	Output:
	[
	["ate","eat","tea"],
	["nat","tan"],
	["bat"]
	]
	Note:
	
	All inputs will be in lowercase.
	The order of your output does not matter.
	
	 * **/
	
	public static List<List<String>> groupAnagrams(String[] strs) {
		if(strs == null || strs.length == 0){
			return null;
		}
		
		List<List<String>> result = new ArrayList<List<String>>();
		Map<String,List<String>> judge = new HashMap();
		for (String key : strs) {
			char[] value = key.toCharArray();
			Arrays.sort(value);
			String tmp1 = new String(value);
			List<String> keyvalue = judge.get(tmp1);
			if(keyvalue == null){
				 keyvalue = new ArrayList<>();
			}
			keyvalue.add(key);
			judge.put(tmp1, keyvalue);
		}
		
		for (List<String> string : judge.values()) {
			result.add(string);
		}
		
		return result;
		 
    }
	
	public static void main(String[] args) {
		
		System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
	}

}
