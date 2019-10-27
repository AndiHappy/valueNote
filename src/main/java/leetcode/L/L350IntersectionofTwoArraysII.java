package leetcode.L;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author guizhai
 *
 */
public class L350IntersectionofTwoArraysII {

 /**
 Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

  */
 public int[] intersect(int[] nums1, int[] nums2) {
  ArrayList<Integer> resulttmp = new ArrayList<Integer>();
  HashMap<Integer,Integer> judge = new HashMap<Integer,Integer>();
  
  for(int i =0; i< nums1.length;i++){
     judge.put(nums1[i], judge.getOrDefault(nums1[i], 0) + 1);
  }
  
  for(int i = 0; i < nums2.length;i++){
      if(judge.containsKey(nums2[i]) && judge.get(nums2[i]) > 0 ){
          resulttmp.add(nums2[i]);
          judge.put(nums2[i],judge.get(nums2[i])-1);
      }
  }
  
  int[] result = new int[resulttmp.size()];
  for(int i = 0; i < result.length ; i++){
      result[i] = resulttmp.get(i);
  }
  return result;
}
 
 public static void main(String[] args) {
  HashMap<Integer,Integer> judge = new HashMap<Integer,Integer>();
  judge.getOrDefault(1, 0);
 

 }

}
