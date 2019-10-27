package leetcode.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author guizhai
 *
 */
public class L347TopKFrequentElements {

 /**
 Given a non-empty array of integers, return the k most frequent elements.
 
 Example 1:
 
 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]
 Example 2:
 
 Input: nums = [1], k = 1
 Output: [1]
 Note:
 
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 
  */

 /***
  input: array k
  output: array
  dealwith: find k most frequent elements
  
  需要统计出来所有元素的频次，还有保留对应的元素的值，这样我们就需要一个sortMap。
  
  TreeMap，作为一个比较合适的排序key的map数据
  
  * 
  * */

 public List<Integer> topKFrequent(int[] nums, int k) {
  List<Integer> result = new ArrayList<Integer>(k);
  Map<Integer, Integer> tmp = new HashMap<Integer, Integer>();
  for (int i = 0; i < nums.length; i++) {
   tmp.put(nums[i], tmp.getOrDefault(nums[i], 0) + 1);
  }
  TreeMap<Integer, List<Integer>> timesToValue = new TreeMap<Integer, List<Integer>>();
  
  for (Integer key : tmp.keySet()) {
   List<Integer> value = timesToValue.getOrDefault(tmp.get(key), new ArrayList<Integer>());
   value.add(key);
   timesToValue.put(tmp.get(key), value);
  }

  while (result.size() < k) {
   Map.Entry<Integer, List<Integer>> entry = timesToValue.pollLastEntry();
   result.addAll(entry.getValue());
  }

  return result.subList(0, k);

 }

 public static void main(String[] args) {
  L347TopKFrequentElements test = new L347TopKFrequentElements();
  System.out.println(test.topKFrequent(new int[] {1,1,1,2,2,3}, 2));
 }

}
