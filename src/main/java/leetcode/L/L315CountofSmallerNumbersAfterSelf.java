package leetcode.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L315CountofSmallerNumbersAfterSelf {

 /**
 You are given an integer array nums and you have to return a new counts array.
 The counts array has the property where counts[i] is the number of smaller elements 
 to the right of nums[i].
 
 Example:
 
 Input: [5,2,6,1]
 Output: [2,1,1,0] 
 Explanation:
 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.
 
  */
 
 public List<Integer> countSmaller(int[] nums) {
  List<Integer> result = new ArrayList<Integer>();
  for (int i = nums.length-1; i >=0; i--) {
   int small = 0;
   for (int j = i+1; j < nums.length; j++) {
    if(nums[j] < nums[i]) {
     small++;
    }
   }
   result.add(0, small);
  }
  return result;
 }
 
 /**
  * 上述的算法看着非常像算法导论里面的插入排序，
  * 我们知道，算法导论里面的插入排序，后面紧跟的就是Merge排序的优化
  * 这个问题，是否可以使用合并排序，如果使用合并排序，又需要哪些优化点，怎么使用？
  * 
  * */

 class Pair {
  int index;
  int val;

  public Pair(int index, int val) {
   this.index = index;
   this.val = val;
  }
  @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+index+","+val+"]";
	}
 }

 public List<Integer> countSmaller_merge(int[] nums) {
  List<Integer> res = new ArrayList<>();
  if (nums == null || nums.length == 0) {
   return res;
  }

  Pair[] arr = new Pair[nums.length];
  Integer[] smaller = new Integer[nums.length];
  Arrays.fill(smaller, 0);

  for (int i = 0; i < nums.length; i++) {
   arr[i] = new Pair(i, nums[i]);
  }

  mergeSort(arr, smaller);
  System.out.println(Arrays.toString(arr));

  return Arrays.asList(smaller);
 }

 /**
  * mergeSort 0 到 6 
  * mergeSort 0到3 ，3到6
  * mergeSort 0到1 1到3
  * */
 private Pair[] mergeSort(Pair[] arr, Integer[] smaller) {
  if (arr.length <= 1) {
   return arr;
  }
  int mid = arr.length / 2;
  Pair[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid), smaller);
  Pair[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length), smaller);
  // 这个时候left和right 均是已经排列好的，我们要保持这一点，就需要排序，这个是比对的排序
  // 当 left[i].val <= right[j].val  
  
  for (int i = 0, j = 0; i < left.length || j < right.length;) {
   if (j == right.length || i < left.length && left[i].val <= right[j].val) {
    arr[i + j] = left[i];
    smaller[left[i].index] += j;
    i++;
   } else {
    arr[i + j] = right[j];
    j++;
   }
  }
  
  System.out.println(Arrays.toString(left));
  System.out.println(Arrays.toString(right));
  System.out.println("smaller:" + Arrays.toString(smaller));

  
  return arr;
 }

 public static void main(String[] args) {
  L315CountofSmallerNumbersAfterSelf test = new L315CountofSmallerNumbersAfterSelf();
  int[] value = new int[] { 4, 3, 7, 5, 2, 6, 1 };
  System.out.println(Arrays.toString(value));
  List<Integer> res = test.countSmaller_merge(value);
  System.out.println(Arrays.toString(res.toArray()));
 }

}
