package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

 public static List<List<String>> groupAnagrams_right(String[] strs) {
  if (strs == null || strs.length == 0) {
   return null;
  }

  Map<String, List<String>> judge = new HashMap<String, List<String>>();
  for (String key : strs) {
   char[] value = key.toCharArray();
   Arrays.sort(value);
   String tmp1 = new String(value);
   if (!judge.containsKey(tmp1))
    judge.put(tmp1, new ArrayList<String>());
   judge.get(tmp1).add(key);
  }
  

  return new ArrayList<List<String>>(judge.values());

 }

 public static List<List<String>> groupAnagrams(String[] strs) {
  if (strs == null || strs.length == 0) {
   return null;
  }

  Map<Integer, List<String>> judge = new HashMap<Integer, List<String>>();
  for (String key : strs) {
   char[] value = key.toCharArray();
   Integer tmp1 = getSum(value);
   if (!judge.containsKey(tmp1))
    judge.put(tmp1, new ArrayList<String>());
   judge.get(tmp1).add(key);
  }

  return new ArrayList<List<String>>(judge.values());

 }

 private static Integer getSum(char[] value) {
  int sum = 0;
  for (char c : value) {
   sum += c;
  }
  return sum;
 }

 public static void main(String[] args) {

  System.out.println(groupAnagrams(new String[] { "cab","tin","pew","duh","may","ill","buy","bar","max","doc" }));
 }

}
