package leetcode.L;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L215KthLargestElementinanArray {

 /**
Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

  */
 
 /**
  * 如果是一个或者二个的时候，我们直接的遍历，保持一个或者两个的比较，更新
  * 这一个或者两个临时声明的变量，但是如果是K的话，该如何保证
  * */
 public int findKthLargest_wrong(int[] nums, int k) {
  k = nums.length - k;
  int lo = 0;
  int hi = nums.length - 1;
  while (lo < hi) {
      final int j = partition(nums, lo, hi);
      if(j < k) {
          lo = j + 1;
      } else if (j > k) {
          hi = j - 1;
      } else {
          break;
      }
  }
  return nums[k];
}

private int partition(int[] a, int lo, int hi) {
  int i = lo;
  int j = hi + 1;
  while(true) {
      while(i < hi && less(a[++i], a[lo]));
      while(j > lo && less(a[lo], a[--j]));
      if(i >= j) {
          break;
      }
      exch(a, i, j);
  }
  exch(a, lo, j);
  return j;
}

private void exch(int[] a, int i, int j) {
  final int tmp = a[i];
  a[i] = a[j];
  a[j] = tmp;
}

private boolean less(int v, int w) {
  return v < w;
}

/**
// pseudocode
 function partition(list, left, right, pivotIndex)
     pivotValue := list[pivotIndex]
     swap list[pivotIndex] and list[right]  // Move pivot to end
     storeIndex := left
     for i from left to right-1
         if list[i] < pivotValue
             swap list[storeIndex] and list[i]
             increment storeIndex
     swap list[right] and list[storeIndex]  // Move pivot to its final place
     return storeIndex 
 * */
public int partition(int[] list,int left,int right,int pivotIndex) {
 int pivotValue = list[pivotIndex];
 swap(list,right,pivotIndex);
 int storeindex = left;
 for (int i = left; i < right; i++) {
  if(list[i] < pivotValue) {
   swap(list, i, storeindex);
   storeindex++;
  }
 }
 swap(list,right,storeindex);
 return storeindex;
}
 
 private void swap(int[] list, int right, int pivotIndex) {
  int tmp = list[pivotIndex];
  list[pivotIndex] = list[right];
  list[right] = tmp;
}
 
//Returns the k-th smallest element of list within left..right inclusive
// (i.e. left <= k <= right).
/*
function select(list, left, right, k)
   if left = right        // If the list contains only one element,
       return list[left]  // return that element
   pivotIndex  := ...     // select a pivotIndex between left and right,
                          // e.g., left + floor(rand() % (right - left + 1))
   pivotIndex  := partition(list, left, right, pivotIndex)
   // The pivot is in its final sorted position
   if k = pivotIndex
       return list[k]
   else if k < pivotIndex
       return select(list, left, pivotIndex - 1, k)
   else
       return select(list, pivotIndex + 1, right, k - pivotIndex)  //The part of the list after pivotIndex has pivotIndex less elements
*/

 public int select(int[] list,int left,int right,int k) {
  if(left == right) return list[left]; // if the list contains only one element, return the element
  int pivotIndex = left+(right-left)/2; // select a pivotindex between left and right
  pivotIndex = partition(list,left,right, pivotIndex); // get the pivotindex corrent index of the list
  if(k == pivotIndex) return list[k];
  else if(k < pivotIndex) return select(list, left, pivotIndex-1, k);
  else return select(list, pivotIndex+1, right, k);
 }
 
 //要求最大值
 public int findKthLargest(int[] nums, int k) {
  return select(nums,0,nums.length-1,nums.length-k);
}     

 public static void main(String[] args) {
  int[] list = new int[] {3,2,1,5,6,4,11,7,0,5,13};
  L215KthLargestElementinanArray test = new L215KthLargestElementinanArray();
  int r = test.select(list,0,list.length-1, 5);
  System.out.println(Arrays.toString(list));
  System.out.println(r);
  
  int rr = test.findKthLargest(list, 2);
  System.out.println(rr);

  rr = test.findKthLargest(new int[] {2,1}, 1);
  System.out.println(rr);
 }

}
