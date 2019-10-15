package leetcode.L36ToL70;

import java.util.Arrays;

/**
 * @author zhailz
 * 2018年11月21日 上午10:34:06
 */
public class L41FirstMissingPositive {
 /**
 Given an unsorted integer array, find the smallest missing positive integer.
 Example 1:
 Input: [1,2,0]
 Output: 3
 Example 2:
 Input: [3,4,-1,1]
 Output: 2
 Example 3:
 Input: [7,8,9,11,12]
 Output: 1
 Note:
 Your algorithm should run in O(n) time and uses constant extra space.
 
 * 
 * **/

 /**
 * 分析思路，维护连续段的大小，O(n)的情况下，只能遍历一下，然后就想不出来了：
 * 
 * The key here is to use swapping to keep constant space and 
 * also make use of the length of the array, 
 * which means there can be at most n positive integers. 
 * So each time we encounter an valid integer, 
 * find its correct position and swap. Otherwise we continue.
 * 
 * 正确的思路：根据元素的下标进行判断，因为下标是有顺序的，从0排到n,我们只需要把对应的元素，也就是0挪到0的位置，1挪到1的位置，
 * 然后再次的循环，判断下标的位置上的值，和下标是不是对应的。
 * 
 * 另外还有一个点就是：重复元素的问题。
 * 
 * 思维非常的巧妙
 * */

 public int firstMissingPositive_(int[] A) {
  int i = 0;
  while (i < A.length) {
   if (A[i] == i + 1 || A[i] <= 0 || A[i] > A.length)
    i++;
   else if (A[A[i] - 1] != A[i])
    swap(A, i, A[i] - 1);
   else
    i++;
  }
  i = 0;
  while (i < A.length && A[i] == i + 1)
   i++;
  return i + 1;
 }
 

 public int firstMissingPositive(int[] A) {
  int i = 0;
  while (i < A.length) {
   if (A[i] == i + 1 || A[i] <= 0 || A[i] > A.length) {
    i++;
   } else if (i + 1 != A[i]) {//存在重复元素的话，这个值会一直的循环下去
    //A[A[i] -1] 数组内该下标对应的位置
    //下标：A[i]-1 但是序号为A[i]
    if (A[A[i] - 1] == A[i]) {
     i++;
    } else {
     swap(A, i, A[i] - 1);
    }

    System.out.println(Arrays.toString(A));
    //这个地方不能i++，因为如果采用了i++，那么转移过来的元素，就可能会产生错误的结果，例如一个数和1转译了以后，直接的i++，那么这个位置就的值1就不可能
    //在转译到对应的位置了。
    //i++;
    //但是有一个问题，就是重复的元素，第一个重复的元素，转移到正确的位置，第二个重复的元素如果继续的替换的话，就会形成死循环
    //所以就剩下判断重复的问题了
    //            	if(A[A[i] -1] == (A[i]-1))
   } else {
    i++;
   }
  }
  System.out.println(Arrays.toString(A));
  i = 0;
  while (i < A.length && A[i] == i + 1)
   i++;
  return i + 1;
 }

 private void swap(int[] A, int i, int j) {
  int temp = A[i];
  A[i] = A[j];
  A[j] = temp;
 }

 public static void main(String[] args) {
  L41FirstMissingPositive test = new L41FirstMissingPositive();
  System.out.println(test.firstMissingPositive(new int[] { 1, 2, 0 }));
  System.out.println(test.firstMissingPositive(new int[] { 3, 4, 9, 7 }));
  System.out.println(test.firstMissingPositive(new int[] { 3, 3, 4, 9, 7 }));
  System.out.println(test.firstMissingPositive(new int[] { 3, 4, -1, 1 }));
 }

}
