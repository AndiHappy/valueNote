package leetcode.L;

/**
 * @author guizhai
 *
 */
public class L334IncreasingTripletSubsequence {

 /**
 
 Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 
 Formally the function should:
 
 Return true if there exists i, j, k
 such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 
 Example 1:
 
 Input: [1,2,3,4,5]
 Output: true
 Example 2:
 
 Input: [5,4,3,2,1]
 Output: false
 
  */

 public boolean increasingTriplet(int[] nums) {
  if (nums == null || nums.length < 3) {
   return false;
  }
  Integer first = null;
  Integer second = null;
  // 遍历数组中的元素，然后去塞上面的三个元素
  for (int i = 0; i < nums.length; i++) {
   if (first == null) {
    first = nums[i];
   } else if (first != null && nums[i] > first) {
    if (second == null) {
     second = nums[i];
    } else if (nums[i] > second) {
     return true;
    } else {
     second = nums[i];
    }
   } else if (first != null && nums[i] < first) {
    first = nums[i];
   }
  }
  return false;
 }
 
 // 看看人家优化后的方案
 
 public boolean increasingTriplet_Pursue(int[] nums) {
  // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
  int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
  for (int n : nums) {
      if (n <= small) { small = n; } // update small if n is smaller than both
      else if (n <= big) { big = n; } // update big only if greater than small but smaller than big
      else return true; // return if you find a number bigger than both
  }
  return false;
}

 public static void main(String[] args) {
  L334IncreasingTripletSubsequence test = new L334IncreasingTripletSubsequence();
  System.out.println(test.increasingTriplet(new int[] {1,2,3,4,5,6}));
  System.out.println(test.increasingTriplet(new int[] {6,5,4,3,2,1}));
  
  System.out.println(test.increasingTriplet(new int[] {6,5,7,3,2,9}));

 }

}
